package com.open.portal.service.image;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.open.portal.api.exception.http.ForbiddenException;
import com.open.portal.api.exception.http.NotFoundException;
import com.open.portal.domain.image.Image;
import com.open.portal.domain.image.data.ImageBBSentRequestConvertedDto;
import com.open.portal.domain.image.data.ImageResponseDto;
import com.open.portal.domain.user.Permission;
import com.open.portal.domain.user.User;
import com.open.portal.integration.imgbb.ImageManager;
import com.open.portal.mapper.ImageMapper;
import com.open.portal.repository.ImageRepository;
import com.open.portal.repository.UserRepository;
import com.open.portal.service.auth.JwtService;
import com.open.portal.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageManager imageManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public Image create(String base64Image, Integer userId, String token) {
        log.info("Starting image creation");

        if (Optional.ofNullable(base64Image).isEmpty()) {
            log.error("Base64 image is null", NullPointerException.class);
            throw new NullPointerException("Base64 image is null");
        }

        User user = userService.readDomainById(userId);

        String username = jwtService.extractUserName(token.substring(7));
        User userCreator = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));

        if (!(Objects.equals(user, userCreator) || userCreator.getPermission() != Permission.ADMIN)) {
            log.error("User does not have permission", ForbiddenException.class);
            throw new ForbiddenException("User does not have permission");
        }

        ImageBBSentRequestConvertedDto responseBody = imageManager.send(base64Image);
        
        Image image = ImageMapper.toDomain(responseBody);
        image.setUser(userService.readDomainById(userId));
        image.setDateTime(LocalDateTime.now());

        return imageRepository.save(image);
    }

    public ImageResponseDto readById(Integer idImage) {
        Image image = imageRepository.findByIdAndDeletedByIsNull(idImage).orElseThrow(() -> new NotFoundException("Image not found"));

        return ImageMapper.toResponseDto(image);
    }

    public void deleteById(Integer idImage, String token) {
        log.info("Starting image deletion");

        String username = jwtService.extractUserName(token.substring(7));
        User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));

        Image image = imageRepository.findById(idImage).orElseThrow(() -> new NotFoundException("Image not found"));

        if (!(image.getUser().getId() == user.getId() || user.getPermission() != Permission.ADMIN)) {
            log.error("User does not have permission", ForbiddenException.class);
            throw new ForbiddenException("User does not have permission");
        }

        String resopnseBody = imageManager.delete(image.getDeleteUrl());
        image.setDeletedBy(user);

        imageRepository.save(image);
    }
}