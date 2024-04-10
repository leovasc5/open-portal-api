package com.open.portal.service.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.open.portal.api.exception.http.BadRequestException;
import com.open.portal.api.exception.http.NoContentException;
import com.open.portal.api.exception.http.NotFoundException;
import com.open.portal.domain.category.Category;
import com.open.portal.domain.category.CategoryOthers;
import com.open.portal.domain.channel.Channel;
import com.open.portal.domain.city.City;
import com.open.portal.domain.city.CityOthers;
import com.open.portal.domain.form.Form;
import com.open.portal.domain.user.User;
import com.open.portal.integration.NotificationChannel;
import com.open.portal.integration.NotificationChannelFactory;
import com.open.portal.repository.CategoryOthersRepository;
import com.open.portal.repository.CategoryRepository;
import com.open.portal.repository.ChannelRepository;
import com.open.portal.repository.CityOthersRepository;
import com.open.portal.repository.CityRepository;
import com.open.portal.repository.FormRepository;
import com.open.portal.repository.UserRepository;
import com.open.portal.service.auth.JwtService;
import com.open.portal.service.notification.NotificationService;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired 
    private CityRepository cityRepository;

    @Autowired
    private CityOthersRepository cityOthersRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryOthersRepository categoryOthersRepository;

    @Async
    public CompletableFuture<Void> create(Form form) {
        log.info("Iniciando criação do formulário vindo do e-mail: " + form.getContactEmail());

        validateExclusiveFields(form.getCity(), form.getCityOthers(),
        "Não é possível ter uma cidade e uma cidade personalizada ao mesmo tempo.");

        validateExclusiveFields(form.getCategory(), form.getCategoryOthers(),
        "Não é possível ter uma categoria e uma categoria personalizada ao mesmo tempo.");

        if (Objects.nonNull(form.getCity())) {
            Optional<City> city = cityRepository.findById(form.getCity().getId());

            if (city.isEmpty()) {
                log.error("Cidade não encontrada.", NotFoundException.class);
                throw new NotFoundException("Cidade não encontrada.");
            }

            form.setCity(city.get());
        } else {
            CityOthers cityOthers = cityOthersRepository.save(new CityOthers(form.getCityOthers().getName()));
            form.setCityOthers(cityOthers);
            form.setCity(new City(cityOthers.getName()));
        }

        if (Objects.nonNull(form.getCategory())) {
            Optional<Category> category = categoryRepository.findById(form.getCategory().getId());

            if (category.isEmpty()) {
                log.error("Categoria não encontrada.", NotFoundException.class);
                throw new NotFoundException("Categoria não encontrada.");
            }

            form.setCategory(category.get());
        } else {
            CategoryOthers categoryOthers = categoryOthersRepository.save(new CategoryOthers(form.getCategoryOthers().getName()));
            form.setCategoryOthers(categoryOthers);
            form.setCategory(new Category(categoryOthers.getName()));
        }

        System.out.println("PONTO A");

        List<Channel> activeChannelTypes = channelRepository.findAllByIsActiveTrue();
        System.out.println("PONTO B");

        if (activeChannelTypes.isEmpty()) {
            log.error("Não há canais de notificação ativos.", NoContentException.class);
            throw new NoContentException("Não há canais de notificação ativos.");
        }
        System.out.println("PONTO C");

        List<User> receivers = userRepository.findByIsReceiverAndDeletedByIsNull(true);
        form.setDateTime(LocalDateTime.now());
        System.out.println("PONTO D");

        List<NotificationChannel> activeChannels = NotificationChannelFactory.createActiveChannels(activeChannelTypes);
        System.out.println("PONTO E");


        return CompletableFuture.runAsync(() -> {
            activeChannels.forEach(channel -> {
                channel.sendNotification(form, receivers);
            });

            Form formSent = formRepository.save(form);


        System.out.println("PONTO G");


            if (activeChannelTypes.stream().anyMatch(channel -> channel.getId() == 1)) {
                notificationService.createNotification(formSent, null, channelRepository.findById(1).get());
                activeChannelTypes.removeIf(channel -> channel.getId() == 1);
            }
        System.out.println("PONTO H");


            activeChannelTypes.forEach(channel -> {
                receivers.forEach(receiver -> {
                    notificationService.createNotification(formSent, receiver, channelRepository.findById(channel.getId()).get());
                });
            });

            log.info("Formulário criado com sucesso.");
        });
    }

    public List<Form> read() {
        List<Form> forms = formRepository.findByDeletedByIsNull();
        
        if (forms.isEmpty()) {
            log.error("Não há formulários cadastrados", NoContentException.class);
            throw new NoContentException("Não há formulários cadastrados.");
        }

        return forms;
    }

    public Form readById(Integer idForm) {
        Optional<Form> form = formRepository.findByIdAndDeletedByIsNull(idForm);

        if (form.isEmpty()) {
            log.error("Formulário não encontrado.", NotFoundException.class);
            throw new NoContentException("Formulário não encontrado.");
        }

        return form.get();
    }

    public byte[] exportToExcel() throws IOException {
        List<Form> forms = read();
    
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Formulários");
        
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Nome", "E-mail", "Empresa", "Categoria", "Cidade", "Mensagem", "Data e Hora"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int rowNum = 1;
        for (Form form : forms) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(form.getAuthorName());
            row.createCell(1).setCellValue(form.getContactEmail());
            row.createCell(2).setCellValue(form.getBusinessName());

            String category = returnCategoryName(form.getCategory(), form.getCategoryOthers());
            row.createCell(3).setCellValue(category);

            String city = returnCityName(form.getCity(), form.getCityOthers());
            row.createCell(4).setCellValue(city);

            row.createCell(5).setCellValue(form.getDescription());
                
            LocalDateTime dateTime = form.getDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);

            row.createCell(6).setCellValue(formattedDateTime);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
    
        return outputStream.toByteArray();
    }

    public void delete(Integer idForm, String token) {
        log.info("Iniciando deleção do formulário de id: " + idForm);

        Optional<Form> form = formRepository.findById(idForm);

        if (form.isEmpty()) {
            log.error("Formulário não encontrado.", NotFoundException.class);
            throw new NotFoundException("Formulário não encontrado.");
        }

        String username = jwtService.extractUserName(token.substring(7));
        Optional<User> deletedBy = userRepository.findByEmail(username);

        if (deletedBy.isEmpty()) {
            log.error("Usuário não encontrado.", NotFoundException.class);
            throw new NotFoundException("Usuário não encontrado.");
        }

        form.get().setDeletedBy(deletedBy.get());
        formRepository.save(form.get());

        return;
    }

    private void validateExclusiveFields(Object field, Object otherField, String errorMessage) {
        if (Objects.nonNull(field) && Objects.nonNull(otherField)) {
            log.error(errorMessage, IllegalArgumentException.class);
            throw new BadRequestException(errorMessage);
        }
    }

    private String returnCityName(City city, CityOthers cityOthers) {
        if (Objects.nonNull(city)) {
            return city.getName();
        } else {
            return cityOthers.getName();
        }
    }

    private String returnCategoryName(Category category, CategoryOthers categoryOthers) {
        if (Objects.nonNull(category)) {
            return category.getName();
        } else {
            return categoryOthers.getName();
        }
    }
}