package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.usecase.CourseSearchAndLookUseCase;
import com.example.ye4leeyu_back.application.usecase.ManageCourseUseCase;
import com.example.ye4leeyu_back.presentation.response.CourseDetailResponse;
import com.example.ye4leeyu_back.presentation.response.LikeCountResponse;
import com.example.ye4leeyu_back.presentation.response.PurchaseInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {
    private final CourseSearchAndLookUseCase courseSearchAndLookUseCase;
    private final ManageCourseUseCase manageCourseUseCase;

    @GetMapping("/course/detail")
    public ResponseEntity<CourseDetailResponse> getCourseDetail(@RequestParam Long courseId) {
        return ResponseEntity.ok(courseSearchAndLookUseCase.getCourseDetail(courseId));
    }

    @PostMapping("/course/detail")
    public ResponseEntity<Void> createStudentCourseBlock(@RequestParam Long courseBlockId){
        manageCourseUseCase.createCourse(courseBlockId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/course/like")
    public ResponseEntity<LikeCountResponse> likeCourse(@RequestParam Long courseId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(manageCourseUseCase.likeCourse(courseId));
    }

    @GetMapping("/course/purchase")
    public ResponseEntity<PurchaseInfoResponse> getPurchaseCourseInfo(@RequestParam Long courseId, Long courseBlockId) {
        return ResponseEntity.ok(courseSearchAndLookUseCase.getPurchaseInfo(courseId, courseBlockId));
    }
}
