package com.open.portal.service.auth.impl;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.open.portal.domain.user.Permission;
import com.open.portal.domain.user.User;

import com.open.portal.api.dao.request.SignUpRequest;
import com.open.portal.api.dao.request.SignInRequest;
import com.open.portal.api.dao.response.JwtAuthenticationResponse;
import com.open.portal.api.exception.http.ForbiddenException;
import com.open.portal.api.exception.http.NotFoundException;
import com.open.portal.repository.UserRepository;
import com.open.portal.service.auth.AuthenticationService;
import com.open.portal.service.auth.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder().name(request.getName()).email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .permission(Permission.USER).phoneNumber(request.getPhoneNumber())
                .isReceiver(request.getIsReceiver()).dateTime(LocalDateTime.now()).deletedBy(null)
                .build();
        
        userRepository.save(user);
        
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ForbiddenException("E-mail ou senha inválida!"));

        if (user.getDeletedBy() != null) {
            throw new NotFoundException("Usuário não encontrado!");
        }

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}