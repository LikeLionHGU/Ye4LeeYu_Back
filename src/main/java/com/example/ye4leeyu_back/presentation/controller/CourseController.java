package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.usecase.CourseSearchAndLookUseCase;
import com.example.ye4leeyu_back.application.usecase.ManageCourseUseCase;
import com.example.ye4leeyu_back.application.usecase.ManageStudentInfoUseCase;
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
    private final ManageStudentInfoUseCase manageStudentInfoUseCase;

    @GetMapping("/course/detail")
    public ResponseEntity<CourseDetailResponse> getCourseDetail(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long courseId) {
        if (customUserDetails == null) {
            return ResponseEntity.ok(courseSearchAndLookUseCase.getCourseDetail(null, courseId));
        }
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
    public ResponseEntity<RecommendCourseResponse> getRecommendCourse(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        String location = null;
        if(customUserDetails == null){
             location = "서울";
            return ResponseEntity.ok(courseSearchAndLookUseCase.getRecommendCourse(null, location));
        }
        location = manageStudentInfoUseCase.getStudentInfo(customUserDetails.getKakaoId()).getAddress();
        return ResponseEntity.ok(courseSearchAndLookUseCase.getRecommendCourse(customUserDetails.getKakaoId(), location));
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
                                                              @RequestParam(required = false) Integer lowestPrice,
                                                              @RequestParam(required = false) Boolean onlyGroup,
                                                              @RequestParam(required = false) Boolean onlyIndividual) {
        if(customUserDetails == null){
            return ResponseEntity.ok(courseSearchAndLookUseCase.searchCourse(null, searchWord, city, district, sportType, disabilityType, date, highestPrice, lowestPrice, onlyGroup, onlyIndividual, pageRequest.of()));
        }
        return ResponseEntity.ok(courseSearchAndLookUseCase.searchCourse(customUserDetails.getKakaoId(), searchWord, city, district, sportType, disabilityType, date, highestPrice, lowestPrice, onlyGroup, onlyIndividual, pageRequest.of()));
    }
}
