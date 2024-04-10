package com.open.portal.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.portal.api.exception.http.UnprocessableEntityException;
import com.open.portal.domain.image.Image;
import com.open.portal.domain.image.data.ImageBBSendResponseDto;
import com.open.portal.domain.image.data.ImageBBSentRequestConvertedDto;
import com.open.portal.domain.image.data.ImageResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ImageMapper {
    public static ImageResponseDto toResponseDto(Image domain) {
        return ImageResponseDto.builder()
                .id(domain.getId())
                .displayUrl(domain.getDisplayUrl())
                .deleteUrl(domain.getDeleteUrl())
                .build();
    }

    public ImageBBSentRequestConvertedDto convertJsonToDto(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ImageBBSendResponseDto responseDto = objectMapper.readValue(jsonResponse, ImageBBSendResponseDto.class);

            return convertJsonToDtoConverted(responseDto);
        } catch (Exception e) {
            log.error("Error converting json to dto: {}", e.getMessage());
            throw new UnprocessableEntityException("Error converting json to dto, but the image was uploaded!");
        }
    }

    public ImageBBSentRequestConvertedDto convertJsonToDtoConverted(ImageBBSendResponseDto response) {
        try {
            ImageBBSentRequestConvertedDto dto = new ImageBBSentRequestConvertedDto();

            ImageBBSentRequestConvertedDto.Data data = new ImageBBSentRequestConvertedDto.Data();
            data.setId((String) response.getData().getId());
            data.setTitle((String) response.getData().getTitle());
            data.setUrlViewer((String) response.getData().getUrlViewer());
            data.setUrl((String) response.getData().getUrl());
            data.setDisplayUrl((String) response.getData().getDisplayUrl());
            data.setWidth((Integer) response.getData().getWidth());
            data.setHeight((Integer) response.getData().getHeight());
            data.setSize((Integer) response.getData().getSize());
            data.setTime((Integer) response.getData().getTime());
            data.setExpiration((Integer) response.getData().getExpiration());
            dto.setData(data);

            ImageBBSentRequestConvertedDto.Image image = new ImageBBSentRequestConvertedDto.Image();
            image.setFilename((String) response.getData().getImage().getFilename());
            image.setName((String) response.getData().getImage().getName());
            image.setMime((String) response.getData().getImage().getMime());
            image.setExtension((String) response.getData().getImage().getExtension());
            image.setUrl((String) response.getData().getImage().getUrl());
            data.setImage(image);

            ImageBBSentRequestConvertedDto.Thumb thumb = new ImageBBSentRequestConvertedDto.Thumb();
            thumb.setFilename((String) response.getData().getThumb().getFilename());
            thumb.setName((String) response.getData().getThumb().getName());
            thumb.setMime((String) response.getData().getThumb().getMime());
            thumb.setExtension((String) response.getData().getThumb().getExtension());
            thumb.setUrl((String) response.getData().getThumb().getUrl());
            data.setThumb(thumb);

            data.setDeleteUrl((String) response.getData().getDeleteUrl());
            dto.setSuccess((Boolean) response.getSuccess());
            dto.setStatus((Integer) response.getStatus());

            return dto;
        } catch (Exception e) {
            log.error("Error converting json to dto: {}", e.getMessage());
            throw new UnprocessableEntityException("Error converting json to dto, but the image was uploaded!");
        }
    }

    public static Image toDomain(ImageBBSentRequestConvertedDto dto) {
        return Image.builder()
                .idImgBB(dto.getData().getId())
                .name(dto.getData().getTitle())
                .displayUrl(dto.getData().getUrl())
                .width(dto.getData().getWidth())
                .height(dto.getData().getHeight())
                .size(dto.getData().getSize())
                .time(dto.getData().getTime())
                .expiration(dto.getData().getExpiration())
                .mime(dto.getData().getImage().getMime())
                .extension(dto.getData().getImage().getExtension())
                .deleteUrl(dto.getData().getDeleteUrl())
                .build();
    }
}