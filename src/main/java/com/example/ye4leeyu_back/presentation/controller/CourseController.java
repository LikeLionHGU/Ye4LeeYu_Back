package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.usecase.CourseUseCase;
import com.example.ye4leeyu_back.presentation.response.CourseDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {
    private final CourseUseCase courseUseCase;

    @GetMapping("/course/detail")
    public ResponseEntity<CourseDetailResponse> getCourseDetail(@RequestParam Long courseId) {
        return ResponseEntity.ok(courseUseCase.getCourseDetail(courseId));
    }


}
