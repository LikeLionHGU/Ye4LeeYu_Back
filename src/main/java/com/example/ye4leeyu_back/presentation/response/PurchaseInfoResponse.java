package com.example.ye4leeyu_back.presentation.response;

import com.example.ye4leeyu_back.presentation.response.course.CourseDetailResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PurchaseInfoResponse {
    StudentInfoResponse student;
    CourseDetailResponse course;

    public static PurchaseInfoResponse of(StudentInfoResponse student, CourseDetailResponse course) {
        return PurchaseInfoResponse.builder()
                .student(student)
                .course(course)
                .build();
    }
}
