package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Usuari;

@Component
public class JwtGenerator {


    public String generate(Usuari usuari) {

        Claims claims = Jwts.claims()
                .setSubject(usuari.getNom());
        claims.put("userId", usuari.getIdGoogle());
//        claims.put("role", "admin");


        //TOKEN
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "pes2019")
                .compact();
    }
}
