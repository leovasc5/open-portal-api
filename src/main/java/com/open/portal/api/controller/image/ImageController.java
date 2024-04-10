package com.open.portal.api.controller.image;

import lombok.RequiredArgsConstructor;
import static org.springframework.http.ResponseEntity.*;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.portal.domain.image.Image;
import com.open.portal.domain.image.data.ImageRequestDto;
import com.open.portal.domain.image.data.ImageResponseDto;
import com.open.portal.service.image.ImageService;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    @Autowired
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ImageRequestDto imageRequest, @RequestHeader("Authorization") String token) {
        Image image = imageService.create(imageRequest.getBase64Image(), imageRequest.getUserId(), token);
        URI location = URI.create(String.format("/image/%d", image.getId()));

        return created(location).build();
    }

    @GetMapping("/{idImage}")
    public ResponseEntity<ImageResponseDto> readById(@PathVariable Integer idImage) {
        ImageResponseDto image = imageService.readById(idImage);

        return ok(image);
    }

    @DeleteMapping("/{idImage}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer idImage, @RequestHeader("Authorization") String token) {
        imageService.deleteById(idImage, token);

        return noContent().build();
    }
}
