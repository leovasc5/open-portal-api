package com.open.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.open.portal.domain.image.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByIdAndDeletedByIsNull(Integer id);
}