package com.mirco.employeeControl.security.jwt;

import com.mirco.employeeControl.model.entity.User;
import com.mirco.employeeControl.service.controll.UserService;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Component
public class JwtProvider {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private int expiration;

    private final UserService userService;

    @Autowired
    public JwtProvider( UserService userService) {
        this.userService = userService;
    }

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        Optional<User> user = userService.findByEmail(userDetails.getUsername());
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.get().getEmail());
        claims.put("id_rol", user.get().getIdRol());
        claims.put("id", user.get().getId());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Token mal formado");
        } catch (UnsupportedJwtException e) {
            log.error("Token no soportado");
        } catch (ExpiredJwtException e) {
            log.error("Token expirado");
        } catch (IllegalArgumentException e) {
            log.error("Token vacio");
        } catch (SignatureException e) {
            log.error("Firma no valida");
        }

        return true;
    }
}
