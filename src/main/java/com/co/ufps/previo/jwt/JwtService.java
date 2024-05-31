package com.co.ufps.previo.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.co.ufps.previo.model.entity.Rol;
import com.co.ufps.previo.model.entity.Usuario;
import com.co.ufps.previo.model.response.RolResponse;
import com.co.ufps.previo.model.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String getToken(Usuario usuario) {
        return getToken(new HashMap<>(), usuario);
    }

    private String getToken(Map<String,Object> extraClaims, Usuario usuario) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .claim("usuarioId", usuario.getId())
                .claim(("tipoDocumento"), usuario.getTipoDocumento())
                .claim("documento", usuario.getDocumento())
                .claim("nombre", usuario.getNombre())
                .claim(("email"), usuario.getEmail())
                .claim("telefono", usuario.getTelefono())
                .claim("roles", usuario.getRoles())
                .subject(usuario.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public UserResponse getUserFromToken(String token) {
        Claims claims = getAllClaims(token);
        UserResponse usuarioResponse = new UserResponse();
        usuarioResponse.setId((Integer) claims.get("usuarioId"));
        usuarioResponse.setUsername(claims.getSubject());
        usuarioResponse.setTipoDocumento((String) claims.get("tipoDocumento"));
        usuarioResponse.setDocumento((String) claims.get("documento"));
        usuarioResponse.setNombre((String) claims.get("nombre"));
        usuarioResponse.setEmail((String) claims.get("email"));
        usuarioResponse.setTelefono((String) claims.get("telefono"));
        usuarioResponse.setRoles((List<RolResponse>) claims.get("roles"));
        return usuarioResponse;
    }

    public boolean isTokenValid(String token, UserDetails usuarioDetails) {
        final String usuarioname=getUsernameFromToken(token);
        return (usuarioname.equals(usuarioDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}
