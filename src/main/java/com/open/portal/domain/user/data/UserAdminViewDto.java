package com.open.portal.domain.user.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAdminViewDto {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private Boolean isReceiver;
}