package com.open.portal.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAuthService {
    UserDetailsService userDetailsService();
}
