package com.open.portal.domain.image.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponseDto {
    private Integer id;
    private String displayUrl;
    private String deleteUrl;
}