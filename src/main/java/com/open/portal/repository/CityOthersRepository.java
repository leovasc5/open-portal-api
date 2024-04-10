package com.open.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.open.portal.domain.city.CityOthers;

public interface CityOthersRepository extends JpaRepository<CityOthers, Integer>{
    Optional<CityOthers> findByName(String name);
}
