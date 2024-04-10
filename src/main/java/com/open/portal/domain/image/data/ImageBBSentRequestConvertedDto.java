package com.open.portal.domain.image.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageBBSentRequestConvertedDto {
    private Data data;
    private boolean success;
    private int status;

    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        private String id;
        private String title;
        private String urlViewer;
        private String url;
        private String displayUrl;
        private int width;
        private int height;
        private int size;
        private int time;
        private int expiration;
        private Image image;
        private Thumb thumb;
        private String deleteUrl;
    }

    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
        private String filename;
        private String name;
        private String mime;
        private String extension;
        private String url;
    }

    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Thumb {
        private String filename;
        private String name;
        private String mime;
        private String extension;
        private String url;
    }
}