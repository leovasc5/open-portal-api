package com.open.portal.api.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.portal.domain.category.CategoryOthers;
import com.open.portal.service.category.CategoryOthersService;

import static org.springframework.http.ResponseEntity.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category/others")
@RequiredArgsConstructor
public class CategoryOthersController {
    @Autowired
    private CategoryOthersService categoryOthersService;

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody CategoryOthers categoryOthers) {
        CategoryOthers categoryCreated = categoryOthersService.create(categoryOthers);

        return created(null).body(categoryCreated.getId());
    }
}