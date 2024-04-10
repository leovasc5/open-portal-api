package com.open.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.open.portal.domain.log.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
    
}
