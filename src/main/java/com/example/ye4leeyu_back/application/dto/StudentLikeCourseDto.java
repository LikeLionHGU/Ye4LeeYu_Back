package com.example.ye4leeyu_back.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class StudentLikeCourseDto {
    private Long id;
    private Long courseId;
    private Long studentId;
}
