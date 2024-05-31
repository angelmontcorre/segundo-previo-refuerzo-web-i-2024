package com.co.ufps.previo.service;

import java.util.List;

import com.co.ufps.previo.model.DTO.UsuarioDTO;
import com.co.ufps.previo.model.entity.Usuario;

public interface UserService {

    public List<Usuario> getAllUsers();
    public Usuario getUserById(Integer id);
    public Usuario updateUser(Integer id, UsuarioDTO usuarioDTO);
    public void deleteUser(Integer id);
    

}
