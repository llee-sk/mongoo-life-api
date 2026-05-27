package com.mongoo.life.domain.auth.dto.response;

public record TokenResponse(
        String tokenType,
        String accessToken,
        String refreshToken,
        long accessTokenExpiresIn) {
}