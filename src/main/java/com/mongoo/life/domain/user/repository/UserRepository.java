package com.mongoo.life.domain.user.repository;

import com.mongoo.life.domain.user.entity.User;
import com.mongoo.life.domain.user.type.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndStatus(Long userId, UserStatus userStatus);
}
