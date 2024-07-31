package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.application.usecase.ManageStudentInfoUseCase;
import com.example.ye4leeyu_back.presentation.request.StudentSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final ManageStudentInfoUseCase manageStudentInfoUseCase;

    @PostMapping("/auth/signup")
    public ResponseEntity<Void> signUp(@RequestParam(value="profileImage", required = false) MultipartFile profileImage, @ModelAttribute StudentSignUpRequest request) {
        manageStudentInfoUseCase.createStudent(profileImage, StudentDto.from(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/auth/kakaoLogin")
    public ResponseEntity<String> kakaoLogin(@RequestParam String code) {
        String accessToken = manageStudentInfoUseCase.getKakaoAccessToken(code);
        String kakaoId = manageStudentInfoUseCase.kakaoLogin(accessToken);
        return ResponseEntity.ok(kakaoId);
    }
}
