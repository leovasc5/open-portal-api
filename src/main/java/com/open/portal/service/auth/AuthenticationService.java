package com.open.portal.service.auth;

import com.open.portal.api.dao.request.SignUpRequest;
import com.open.portal.api.dao.request.SignInRequest;
import com.open.portal.api.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
