package com.mongoo.life.domain.auth.controller;

import com.mongoo.life.domain.auth.dto.request.SignUpRequest;
import com.mongoo.life.domain.auth.dto.response.TokenResponse;
import com.mongoo.life.domain.auth.service.SignupService;
import com.mongoo.life.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "인증 관련 Controller")
public class AuthController {
    private final SignupService signupService;

    @Operation(summary = "회원 가입")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<TokenResponse>> signup(@Valid @RequestBody SignUpRequest request){
        return ResponseEntity.ok(ApiResponse.success("회원가입 성공", signupService.signup(request)));
    }
}
