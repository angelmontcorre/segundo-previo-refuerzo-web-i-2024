package com.co.ufps.previo.controller;

import com.co.ufps.previo.jwt.JwtService;
import com.co.ufps.previo.model.DTO.UsuarioDTO;
import com.co.ufps.previo.model.entity.Usuario;
import com.co.ufps.previo.model.response.UserResponse;
import com.co.ufps.previo.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        try {
            List<Usuario> usersResponse = userService.getAllUsers();
            return ResponseEntity.ok(usersResponse);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Integer id) {
        try {
            Usuario usuario = userService.getUserById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = userService.updateUser(id, usuarioDTO);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Integer id){
        this.userService.deleteUser(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", true);
        return ResponseEntity.ok(respuesta);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{token}")
    public ResponseEntity<UserResponse> getUsernameToken(@PathVariable String token) {
        try {
            UserResponse userResponse = jwtService.getUserFromToken(token);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
