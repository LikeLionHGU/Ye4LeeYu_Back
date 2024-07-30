package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.usecase.CourseSearchAndLookUseCase;
import com.example.ye4leeyu_back.application.usecase.ManageCourseUseCase;
import com.example.ye4leeyu_back.presentation.response.CourseDetailResponse;
import com.example.ye4leeyu_back.presentation.response.LikeCountResponse;
import com.example.ye4leeyu_back.presentation.response.PurchaseInfoResponse;
import com.example.ye4leeyu_back.presentation.response.RecommendCourseResponse;
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

    @GetMapping("/course/recommend")
    public ResponseEntity<RecommendCourseResponse> getRecommendCourse(@RequestParam(required = false) String location) {

        if (location == null) {
            location = "서울";
        }

        return ResponseEntity.ok(courseSearchAndLookUseCase.getRecommendCourse(location));
    }

    // 로그인한 사용자는 DB에서 위치를 가져와서 추천해주므로
    // 프론트에서 로그인 감지가 어렵다면 백에서 로그인 상태에 따라 로직을 달리 할 수 있을 것 같다
    @GetMapping("/course/recommend-login")
    public ResponseEntity<RecommendCourseResponse> getRecommendCourse() {

        // 회원의 위치를 가져오는 유즈 케이스 필요
        return ResponseEntity.ok(courseSearchAndLookUseCase.getRecommendCourse("서울"));

    }
}
