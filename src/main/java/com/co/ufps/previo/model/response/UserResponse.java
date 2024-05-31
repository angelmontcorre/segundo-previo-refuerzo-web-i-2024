package com.co.ufps.previo.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String username;
    private String nombre;
    private String tipoDocumento;
    private String documento;
    private String email;
    private String telefono;
    private List<RolResponse> roles;
}
