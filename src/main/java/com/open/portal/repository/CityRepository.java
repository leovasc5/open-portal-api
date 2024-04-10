package com.open.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.open.portal.domain.city.City;

public interface CityRepository extends JpaRepository<City, Integer>{
    List<City> findAllByIsActiveTrue();

    Optional<City> findByIdAndIsActiveTrue(Integer id);
}