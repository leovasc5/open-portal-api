package com.open.portal.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.open.portal.domain.channel.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {
    List<Channel> findAllByIsActiveTrue();
}
