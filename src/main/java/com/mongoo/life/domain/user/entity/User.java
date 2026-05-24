package com.mongoo.life.domain.user.entity;

import com.mongoo.life.domain.user.type.AuthProvider;
import com.mongoo.life.domain.user.type.UserStatus;
import com.mongoo.life.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users",
    uniqueConstraints = {@UniqueConstraint(name = "uq_users_provider_provider_id", columnNames = {"provider", "provider_id"})}
)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, nullable = false, unique = true)
    private String email;

    @Column(length = 255)
    private String password;

    @Column(length = 50, nullable = false, unique = true)
    private String nickname;

    @Column(name = "nickname_changed_at")
    private Instant nicknameChangedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AuthProvider provider;

    @Column(name = "provider_id", length = 50)
    private String providerId;

    @Column(length = 255)
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status;

    @Builder(access = AccessLevel.PRIVATE)
    private User(
            String email,
            String password,
            String nickname,
            Instant nicknameChangedAt,
            AuthProvider provider,
            String providerId,
            String profileImageUrl,
            UserStatus status
    ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.nicknameChangedAt = nicknameChangedAt;
        this.provider = provider;
        this.providerId = providerId;
        this.profileImageUrl = profileImageUrl;
        this.status = status;
    }

    public static User createLocalUser(String email, String encodedPassword, String nickname) {
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .provider(AuthProvider.LOCAL)
                .status(UserStatus.ACTIVE)
                .build();
    }

    public static User createOAuthUser(String email, String nickname, AuthProvider provider, String providerId, String profileImageUrl) {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .provider(provider)
                .providerId(providerId)
                .profileImageUrl(profileImageUrl)
                .status(UserStatus.ACTIVE)
                .build();
    }
}
