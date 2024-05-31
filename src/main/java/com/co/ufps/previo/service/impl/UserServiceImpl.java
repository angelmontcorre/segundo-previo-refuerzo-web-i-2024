package com.co.ufps.previo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.co.ufps.previo.model.DTO.UsuarioDTO;
import com.co.ufps.previo.model.entity.Rol;
import com.co.ufps.previo.model.entity.Usuario;
import com.co.ufps.previo.repository.RolRepository;
import com.co.ufps.previo.repository.UserRepository;
import com.co.ufps.previo.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    @Override
    public List<Usuario> getAllUsers() {
        List<Usuario> usuarios = userRepository.findAll();
        return usuarios;
    }
    @Override
    public Usuario getUserById(Integer id) {
        Usuario usuario = userRepository.getReferenceById(id);
        return usuario;
    }
    
    @Override
    public Usuario updateUser(Integer id, UsuarioDTO usuarioDTO) {
        Usuario usuario = userRepository.getReferenceById(id);
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setTipoDocumento(usuarioDTO.getTipoDocumento());
        usuario.setDocumento(usuarioDTO.getDocumento());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setPassword(usuarioDTO.getPassword());
        
        return usuario = userRepository.save(usuario);
    }
    @Override
    public void deleteUser(Integer id) {
        Usuario usuario = userRepository.getReferenceById(id);
        userRepository.delete(usuario);

    }
    
}
