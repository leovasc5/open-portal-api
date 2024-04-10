package com.open.portal.mapper;

import com.open.portal.domain.user.User;
import com.open.portal.domain.user.data.UserAdminViewDto;

public class UserMapper {
    public static UserAdminViewDto toUserAdminViewDto(User user) {
        return UserAdminViewDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isReceiver(user.getIsReceiver())
                .build();
    }

    public static User toUser(UserAdminViewDto userAdminViewDto) {
        return User.builder()
                .id(userAdminViewDto.getId())
                .name(userAdminViewDto.getName())
                .email(userAdminViewDto.getEmail())
                .phoneNumber(userAdminViewDto.getPhoneNumber())
                .isReceiver(userAdminViewDto.getIsReceiver())
                .build();
    }
}