package com.mongoo.life.global.security.jwt;

import com.mongoo.life.global.security.jwt.exception.InvalidJwtTokenException;
import com.mongoo.life.global.security.jwt.exception.JwtTokenExpiredException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-validity-in-seconds}")
    private long accessTokenExpirationSeconds;

    @Getter
    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpirationSeconds;

    private SecretKey getSigningKey(){
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(Long id,String email){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpirationSeconds * 1000L);

        return Jwts.builder()
                .subject(String.valueOf(id))
                .claim("email", email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String createRefreshToken(Long id){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshTokenExpirationSeconds * 1000L);

        return Jwts.builder()
                .subject(String.valueOf(id))
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .id(UUID.randomUUID().toString())
                .compact();
    }

    private Claims parseClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long getMemberIdFromToken(String token){
        String subject = parseClaims(token).getSubject();
        return Long.valueOf(subject);
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new JwtTokenExpiredException();
        } catch (SecurityException | MalformedJwtException e) {
            throw new InvalidJwtTokenException();
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtTokenException();
        }
    }
}
