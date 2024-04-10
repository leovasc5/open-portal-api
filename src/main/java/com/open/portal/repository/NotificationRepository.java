package com.open.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.open.portal.domain.notification.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    
}