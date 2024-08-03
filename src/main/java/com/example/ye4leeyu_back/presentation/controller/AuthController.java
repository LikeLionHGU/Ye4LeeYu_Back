package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.application.usecase.ManageStudentInfoUseCase;
import com.example.ye4leeyu_back.presentation.request.StudentSignUpRequest;
import com.example.ye4leeyu_back.presentation.response.LoginTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final ManageStudentInfoUseCase manageStudentInfoUseCase;

    @PostMapping("/auth/signup")
    public ResponseEntity<LoginTokenResponse> signUp(@RequestBody StudentSignUpRequest request, @RequestParam String code) {
        String accessToken = manageStudentInfoUseCase.getKakaoAccessToken(code);
        String JwtToken = manageStudentInfoUseCase.createStudent(StudentDto.from(request), accessToken);
        return ResponseEntity.ok(LoginTokenResponse.of(JwtToken));
    }

    @GetMapping("/auth/login")
    public ResponseEntity<LoginTokenResponse> login(@RequestParam String code) {
        String JwtToken = manageStudentInfoUseCase.getJwtToken(code);
        return ResponseEntity.ok(LoginTokenResponse.of(JwtToken));
    }
}
