package com.open.portal.api.controller.form;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.open.portal.domain.form.Form;
import com.open.portal.service.form.FormService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import lombok.RequiredArgsConstructor;
import static org.springframework.http.ResponseEntity.*;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {
    @Autowired
    private final FormService formService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> create(@RequestBody Form form) {
        formService.create(form);

        return accepted().build();
    }
    
    @GetMapping
    public ResponseEntity<List<Form>> read() {
        List<Form> forms = formService.read();

        return ok(forms);
    }

    @GetMapping("/export-xlsx")
    public ResponseEntity<byte[]> exportToExcel() {
        try {
            byte[] excelBytes = formService.exportToExcel();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "formularios.xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{idForm}")
    public ResponseEntity<Form> readById(@PathVariable Integer idForm) {
        Form form = formService.readById(idForm);

        return ok(form);
    }

    @DeleteMapping("/{idForm}")
    public ResponseEntity<Void> delete(@PathVariable Integer idForm, @RequestHeader("Authorization") String token) {
        formService.delete(idForm, token);

        return noContent().build();
    }
}