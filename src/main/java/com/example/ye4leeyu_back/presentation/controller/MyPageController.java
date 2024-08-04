package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.application.usecase.HistoryUseCase;
import com.example.ye4leeyu_back.application.usecase.ManageStudentInfoUseCase;
import com.example.ye4leeyu_back.config.CustomUserDetails;
import com.example.ye4leeyu_back.presentation.request.UpdateStudentInfoRequest;
import com.example.ye4leeyu_back.presentation.response.ProfileResponse;
import com.example.ye4leeyu_back.presentation.response.course.CourseResponse;
import com.example.ye4leeyu_back.presentation.response.HistoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyPageController {
    private final HistoryUseCase historyUseCase;
    private final ManageStudentInfoUseCase manageStudentInfoUseCase;

    @GetMapping("/mypage/exp")
    public ResponseEntity<HistoryResponse> getHistory(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(historyUseCase.getHistory(customUserDetails.getKakaoId()));
    }

    @GetMapping("/mypage/like")
    public ResponseEntity<List<CourseResponse>> getLikeCourse(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(historyUseCase.getLikeCourse(customUserDetails.getKakaoId()));
    }

    @GetMapping("/mypage/course")
    public ResponseEntity<List<CourseResponse>> getCourse(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam boolean isCompleted) {
        return ResponseEntity.ok(historyUseCase.getCourse(customUserDetails.getKakaoId(), isCompleted));
    }

    @PatchMapping("/mypage")
    public ResponseEntity<Void> updateStudentInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody UpdateStudentInfoRequest request){
        manageStudentInfoUseCase.updateStudentInfo(customUserDetails.getKakaoId(), StudentDto.from(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/mypage")
    public ResponseEntity<ProfileResponse> getStudentInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        return ResponseEntity.ok(manageStudentInfoUseCase.getStudentInfo(customUserDetails.getKakaoId()));
    }
}
