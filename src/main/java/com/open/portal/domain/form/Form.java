package com.open.portal.domain.form;

import java.time.LocalDateTime;

import com.open.portal.domain.category.Category;
import com.open.portal.domain.category.CategoryOthers;
import com.open.portal.domain.city.City;
import com.open.portal.domain.city.CityOthers;
import com.open.portal.domain.user.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "form")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, name = "author_name")
    @NotBlank
    private String authorName;

    @Column(length = 64, name = "author_role")
    @NotBlank
    private String authorRole;

    @Column(length = 64, name = "business_name")
    @NotBlank
    private String businessName;
    
    @Column(length = 256, name = "contact_email")
    @NotBlank
    private String contactEmail;

    @Column(length = 1024)
    @NotBlank
    private String description;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "city_others_id")
    private CityOthers cityOthers;

    @ManyToOne
    @JoinColumn(name = "category_others_id")
    private CategoryOthers categoryOthers;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;
}