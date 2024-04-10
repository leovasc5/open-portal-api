package com.open.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.open.portal.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<Integer> findIdByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByIdAndDeletedByIsNull(Integer id);

    List<User> findAllByDeletedByIsNull();

    List<User> findByIsReceiverAndDeletedByIsNull(Boolean isReceiver);
}