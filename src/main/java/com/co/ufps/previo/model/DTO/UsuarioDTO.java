package com.co.ufps.previo.model.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Integer id;
    private String username;
    private String nombre;
    private String tipoDocumento;
    private String documento;
    private String email;
    private String telefono;
    private String password;
    
}
