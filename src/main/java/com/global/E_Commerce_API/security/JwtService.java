package com.global.E_Commerce_API.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtService {

    private static final String secret="7df4f30ccb2aa18c5ed0dabd152e2854b1a4ec7acbbe7f0749b1c1a9ccf5bb6a";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {

    return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).
            setIssuedAt(new Date(System.currentTimeMillis())).
            setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token,UserDetails userDetails) {
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}