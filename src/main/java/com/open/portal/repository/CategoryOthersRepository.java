package com.open.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.open.portal.domain.category.CategoryOthers;

public interface CategoryOthersRepository extends JpaRepository<CategoryOthers, Integer> {
    Optional<CategoryOthers> findByName(String name);
}