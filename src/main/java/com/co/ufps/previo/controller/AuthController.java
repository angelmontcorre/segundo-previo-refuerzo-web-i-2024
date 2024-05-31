package com.co.ufps.previo.controller;

import com.co.ufps.previo.exception.AuthenticationException;
import com.co.ufps.previo.model.response.AuthResponse;
import com.co.ufps.previo.service.impl.AuthService;
import com.co.ufps.previo.model.request.LoginRequest;
import com.co.ufps.previo.model.request.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult)
            throws AuthenticationException {
        if (bindingResult.hasErrors()) {
            throw new AuthenticationException("Credenciales de inicio de sesión inválidas", HttpStatus.BAD_REQUEST);
        }

        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request, BindingResult bindingResult)
            throws AuthenticationException {

        if (bindingResult.hasErrors()) {
            throw new AuthenticationException("Datos de registro inválidos", HttpStatus.BAD_REQUEST);
        }

        AuthResponse response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }
}
