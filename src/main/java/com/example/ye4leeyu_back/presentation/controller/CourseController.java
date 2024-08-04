package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.usecase.CourseSearchAndLookUseCase;
import com.example.ye4leeyu_back.application.usecase.ManageCourseUseCase;
import com.example.ye4leeyu_back.config.CustomUserDetails;
import com.example.ye4leeyu_back.presentation.request.PageRequest;
import com.example.ye4leeyu_back.presentation.response.*;
import com.example.ye4leeyu_back.presentation.response.course.CourseDetailResponse;
import com.example.ye4leeyu_back.presentation.response.course.CourseResponse;
import com.example.ye4leeyu_back.presentation.response.course.RecommendCourseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {
    private final CourseSearchAndLookUseCase courseSearchAndLookUseCase;
    private final ManageCourseUseCase manageCourseUseCase;

    @GetMapping("/course/detail")
    public ResponseEntity<CourseDetailResponse> getCourseDetail(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long courseId) {
        return ResponseEntity.ok(courseSearchAndLookUseCase.getCourseDetail(customUserDetails.getKakaoId(), courseId));
    }

    @PostMapping("/course/detail")
    public ResponseEntity<Void> createStudentCourseBlock(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long courseBlockId){
        manageCourseUseCase.createCourse(customUserDetails.getKakaoId(), courseBlockId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/course/like")
    public ResponseEntity<LikeCountResponse> likeCourse(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long courseId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(manageCourseUseCase.likeCourse(customUserDetails.getKakaoId(), courseId));
    }

    @GetMapping("/course/purchase")
    public ResponseEntity<PurchaseInfoResponse> getPurchaseCourseInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long courseId, Long courseBlockId) {
        return ResponseEntity.ok(courseSearchAndLookUseCase.getPurchaseInfo(customUserDetails.getKakaoId(), courseId, courseBlockId));
    }

    @GetMapping("/course/recommend")
    public ResponseEntity<RecommendCourseResponse> getRecommendCourse(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam(required = false) String location) {

        if (location == null) {
            location = "서울";
        }

        return ResponseEntity.ok(courseSearchAndLookUseCase.getRecommendCourse(customUserDetails.getKakaoId(), location));
    }

    // 로그인한 사용자는 DB에서 위치를 가져와서 추천해주므로
    // 프론트에서 로그인 감지가 어렵다면 백에서 로그인 상태에 따라 로직을 달리 할 수 있을 것 같다
    @GetMapping("/course/recommend-login")
    public ResponseEntity<RecommendCourseResponse> getRecommendCourse(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        // 회원의 위치를 가져오는 유즈 케이스 필요
        return ResponseEntity.ok(courseSearchAndLookUseCase.getRecommendCourse(customUserDetails.getKakaoId(), "서울"));

    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseResponse>> getCourseList(PageRequest pageRequest,
                                                              @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                              @RequestParam(required = false) String searchWord,
                                                              @RequestParam(required = false) String city,
                                                              @RequestParam(required = false) List<String> district,
                                                              @RequestParam(required = false) List<String> sportType,
                                                              @RequestParam(required = false) List<String> disabilityType,
                                                              @RequestParam(required = false) List<LocalDate> date,
                                                              @RequestParam(required = false) Integer highestPrice,
                                                              @RequestParam(required = false) Integer lowestPrice) {
        return ResponseEntity.ok(courseSearchAndLookUseCase.searchCourse(customUserDetails.getKakaoId(), searchWord, city, district, sportType, disabilityType, date, highestPrice, lowestPrice, pageRequest.of()));
    }
}
