package fr.istic.taa.jaxrs.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class TokenUtil {
    private static final String SECRET_KEY = "ma_clé_secrète_tres_longue_et_securisee_123456";

    public static String generateToken(String email, String role) {
        long expirationTime = 86400000L; // 1 day in milliseconds
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);
        System.out.println("Token expiration time: " + expirationTime);

        // Créer un objet Key à partir de la chaîne secrète
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role) // Ajouter le rôle de l'utilisateur
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            // Utilise Jwts.parserBuilder() qui n'est pas déprécié
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static String extractEmail(String token) {
        Claims claims = validateToken(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

}
