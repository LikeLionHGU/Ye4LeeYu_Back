package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.application.usecase.HistoryUseCase;
import com.example.ye4leeyu_back.application.usecase.ManageStudentInfoUseCase;
import com.example.ye4leeyu_back.presentation.request.UpdateStudentInfoRequest;
import com.example.ye4leeyu_back.presentation.response.CourseResponse;
import com.example.ye4leeyu_back.presentation.response.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyPageController {
    private final HistoryUseCase historyUseCase;
    private final ManageStudentInfoUseCase manageStudentInfoUseCase;

    @GetMapping("/mypage/exp")
    public ResponseEntity<HistoryResponse> getHistory() {
        return ResponseEntity.ok(historyUseCase.getHistory());
    }

    @GetMapping("/mypage/like")
    public ResponseEntity<List<CourseResponse>> getLikeCourse() {
        return ResponseEntity.ok(historyUseCase.getLikeCourse());
    }

    @GetMapping("/mypage/course")
    public ResponseEntity<List<CourseResponse>> getCourse(@RequestParam boolean isCompleted) {
        return ResponseEntity.ok(historyUseCase.getCourse(isCompleted));
    }

    @PatchMapping("/mypage")
    public ResponseEntity<Void> updateStudentInfo(@RequestBody UpdateStudentInfoRequest request){
        manageStudentInfoUseCase.updateStudentInfo(StudentDto.from(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
