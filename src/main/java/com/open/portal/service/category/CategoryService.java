package com.open.portal.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.open.portal.api.exception.http.ForbiddenException;
import com.open.portal.api.exception.http.NotFoundException;
import com.open.portal.domain.category.Category;
import com.open.portal.domain.user.Permission;
import com.open.portal.domain.user.User;
import com.open.portal.repository.CategoryRepository;
import com.open.portal.repository.UserRepository;
import com.open.portal.service.auth.JwtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public List<Category> read() {
        List<Category> categories = categoryRepository.findAllByIsActiveTrue();

        if (categories.isEmpty()) {
            throw new NotFoundException("No categories found.");
        }

        return categories;
    }

    public List<Category> readAll() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new NotFoundException("No categories found.");
        }

        return categories;
    }

    public void active(Integer id, String token) {
        log.info("Starting category activation");

        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found."));

        String username = jwtService.extractUserName(token.substring(7));
        User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getPermission() != Permission.ADMIN) {
            log.error("User does not have permission", ForbiddenException.class);
            throw new ForbiddenException("User does not have permission");
        }

        category.setActive(true);
        categoryRepository.save(category);
    }

    public void delete(Integer id, String token) {
        log.info("Starting category deletion");

        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found."));

        String username = jwtService.extractUserName(token.substring(7));
        User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getPermission() != Permission.ADMIN) {
            log.error("User does not have permission", ForbiddenException.class);
            throw new ForbiddenException("User does not have permission");
        }

        category.setActive(false);
        categoryRepository.save(category);
    }
}