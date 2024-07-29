package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.usecase.CourseUseCase;
import com.example.ye4leeyu_back.application.usecase.ManageCourseUseCase;
import com.example.ye4leeyu_back.presentation.response.CourseDetailResponse;
import com.example.ye4leeyu_back.presentation.response.LikeCountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {
    private final CourseUseCase courseUseCase;
    private final ManageCourseUseCase manageCourseUseCase;

    @GetMapping("/course/detail")
    public ResponseEntity<CourseDetailResponse> getCourseDetail(@RequestParam Long courseId) {
        return ResponseEntity.ok(courseUseCase.getCourseDetail(courseId));
    }

    @PostMapping("/course/detail")
    public void createStudentCourseBlock(@RequestParam Long courseBlockId){
        manageCourseUseCase.createCourse(courseBlockId);
    }

    @PostMapping("/course/like")
    public ResponseEntity<LikeCountResponse> likeCourse(@RequestParam Long courseId) {
        return ResponseEntity.ok(manageCourseUseCase.likeCourse(courseId));
    }

}
