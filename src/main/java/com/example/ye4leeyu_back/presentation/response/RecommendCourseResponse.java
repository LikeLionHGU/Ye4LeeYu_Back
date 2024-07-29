package com.example.ye4leeyu_back.presentation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RecommendCourseResponse {
    private List<CourseResponse> adCourse;
    private List<CourseResponse> hotCourse;

    public static RecommendCourseResponse of(List<CourseResponse> adCourse, List<CourseResponse> hotCourse) {
        return RecommendCourseResponse.builder()
                .adCourse(adCourse)
                .hotCourse(hotCourse)
                .build();
    }
}
