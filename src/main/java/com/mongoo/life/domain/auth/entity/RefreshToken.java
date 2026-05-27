package com.mongoo.life.domain.auth.entity;

import com.mongoo.life.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "refresh_token", nullable = false, length = 1000)
    private String refreshToken;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    public RefreshToken(User user, String refreshToken, Instant expiresAt) {
        this.user = user;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }

    public void updateRefreshToken(String refreshToken, Instant expiresAt) {
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }
}
