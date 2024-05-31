package com.co.ufps.previo.service.impl;

import com.co.ufps.previo.jwt.JwtService;
import com.co.ufps.previo.model.entity.Rol;
import com.co.ufps.previo.model.entity.Usuario;
import com.co.ufps.previo.model.request.LoginRequest;
import com.co.ufps.previo.model.request.RegisterRequest;
import com.co.ufps.previo.model.response.AuthResponse;
import com.co.ufps.previo.repository.RolRepository;
import com.co.ufps.previo.repository.UserRepository;
import com.co.ufps.previo.service.iAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService implements iAuthService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            Usuario user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .tokenAccess("Bearer " + token)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Credenciales de inicio de sesión inválidas");
        }
    }

    @Override
    public AuthResponse registerUser(RegisterRequest request) {

        Rol roles = rolRepository.findByNombre("USER").orElseThrow();

        try {
            Usuario user = Usuario.builder()
                    .username(request.getUsername().toUpperCase()) // Conversion to uppercase (optional)
                    .password(passwordEncoder.encode(request.getPassword()))
                    .nombre(request.getNombre().toUpperCase()) // Conversion to uppercase (optional)
                    .tipoDocumento(request.getTipoDocumento().toUpperCase()) // Conversion to uppercase (optional)
                    .documento(request.getDocumento().toUpperCase()) // Conversion to uppercase (optional)
                    .telefono(request.getTelefono().toUpperCase()) // Conversion to uppercase (optional)
                    .email(request.getEmail().toUpperCase()) // Conversion to uppercase (optional)
                    .roles(Collections.singletonList(roles))
                    .build();

            userRepository.save(user);

            return AuthResponse.builder()
                    .tokenAccess("Bearer " + jwtService.getToken(user))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar usuario", e); // Re-throw with user-friendly message and original exception for debugging
        }
    }

}
