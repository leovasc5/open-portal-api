package com.open.portal.api.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.portal.api.dao.request.SignUpRequest;
import com.open.portal.api.dao.request.SignInRequest;
import com.open.portal.api.dao.response.JwtAuthenticationResponse;
import com.open.portal.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ok(authenticationService.signUp(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        JwtAuthenticationResponse requisicao = authenticationService.signIn(request);

        return ok(requisicao);
    }
}