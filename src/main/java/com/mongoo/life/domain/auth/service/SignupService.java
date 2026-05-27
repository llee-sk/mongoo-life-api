package com.mongoo.life.domain.auth.service;

import com.mongoo.life.domain.auth.dto.request.SignUpRequest;
import com.mongoo.life.domain.auth.dto.response.TokenResponse;
import com.mongoo.life.domain.auth.exception.DuplicateEmailException;
import com.mongoo.life.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignupService {
    private final UserRepository userRepository;

    @Transactional
    public TokenResponse signup(@Valid SignUpRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException();
        }


        return null;
    }
}
