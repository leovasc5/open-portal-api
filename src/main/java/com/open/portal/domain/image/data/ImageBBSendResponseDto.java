package com.open.portal.domain.image.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageBBSendResponseDto {
    private Data data;
    private Object success;
    private Object status;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Data {
        private Object id;
        private Object title;
        
        @JsonProperty("url_viewer")
        private Object urlViewer;
        
        private Object url;

        @JsonProperty("display_url")
        private Object displayUrl;

        private Object width;
        private Object height;
        private Object size;
        private Object time;
        private Object expiration;
        private Image image;
        private Thumb thumb;

        @JsonProperty("delete_url")
        private Object deleteUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Image {
        private Object filename;
        private Object name;
        private Object mime;
        private Object extension;
        private Object url;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Thumb {
        private Object filename;
        private Object name;
        private Object mime;
        private Object extension;
        private Object url;
    }
}