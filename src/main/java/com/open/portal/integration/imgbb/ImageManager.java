package com.open.portal.integration.imgbb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.open.portal.domain.image.data.ImageBBDeleteRequestDto;
import com.open.portal.domain.image.data.ImageBBSentRequestConvertedDto;
import com.open.portal.mapper.ImageMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ImageManager {
    @Value("${imgbb.api.key}")
    private String imgbbApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ImageMapper imageMapper;

    public ImageBBSentRequestConvertedDto send(String base64Image) {
        log.info("Sending image to imgbb");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        String url = "https://api.imgbb.com/1/upload?expiration=600&key=" + imgbbApiKey;

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", base64Image);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String responseBody = responseEntity.getBody();
        log.info(responseBody);

        return imageMapper.convertJsonToDto(responseBody);
    }

    public String delete(String deleteUrl) {
        log.info("Deleting image from imgbb");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = deleteUrl + "?key=" + imgbbApiKey;

        ImageBBDeleteRequestDto request = new ImageBBDeleteRequestDto(imgbbApiKey);

        HttpEntity<ImageBBDeleteRequestDto> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

        String responseBody = responseEntity.getBody();

        return responseBody;
    }
}