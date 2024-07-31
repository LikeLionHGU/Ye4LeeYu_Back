package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.application.usecase.ManageStudentInfoUseCase;
import com.example.ye4leeyu_back.presentation.request.StudentSignUpRequest;
import com.example.ye4leeyu_back.presentation.response.LoginTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final ManageStudentInfoUseCase manageStudentInfoUseCase;

    @PostMapping("/auth/signup")
    public ResponseEntity<LoginTokenResponse> signUp(@RequestParam(value="profileImage", required = false) MultipartFile profileImage, @ModelAttribute StudentSignUpRequest request) {
        String accessToken = manageStudentInfoUseCase.getKakaoAccessToken(request.getCode());
        String JwtToken = manageStudentInfoUseCase.createStudent(profileImage, StudentDto.from(request), accessToken);
        return ResponseEntity.ok(LoginTokenResponse.of(JwtToken));
    }
}
