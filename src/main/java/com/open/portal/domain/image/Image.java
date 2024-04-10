package com.open.portal.domain.image;

import java.time.LocalDateTime;

import com.open.portal.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_imgbb", nullable = false, length = 16)
    private String idImgBB;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "display_url", length = 256)
    private String displayUrl;

    @Column(name = "width", nullable = false)
    private int width;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "time", nullable = false)
    private int time;

    @Column(name = "expiration", nullable = false)
    private int expiration;

    @Column(name = "mime", nullable = false, length = 16)
    private String mime;

    @Column(name = "extension", nullable = false, length = 5)
    private String extension;

    @Column(name = "delete_url", nullable = false, length = 256)
    private String deleteUrl;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}