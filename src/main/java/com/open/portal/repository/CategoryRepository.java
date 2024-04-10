package com.open.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.open.portal.domain.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByIsActiveTrue();

    Optional<Category> findByIdAndIsActiveTrue(Integer id);
}