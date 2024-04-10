package com.open.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.open.portal.domain.form.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer>{
    List<Form> findByDeletedByIsNull();

    Optional<Form> findByIdAndDeletedByIsNull(Integer id);
}