package com.open.portal.api.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.portal.domain.category.Category;
import com.open.portal.service.category.CategoryService;

import static org.springframework.http.ResponseEntity.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> read() {
        List<Category> Categorys = categoryService.read();

        return ok(Categorys);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<Category>> readAll() {
        List<Category> Categorys = categoryService.readAll();

        return ok(Categorys);
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<Void> active(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        categoryService.active(id, token);

        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        categoryService.delete(id, token);

        return noContent().build();
    }
}