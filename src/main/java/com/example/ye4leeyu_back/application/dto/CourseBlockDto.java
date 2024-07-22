package com.example.ye4leeyu_back.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CourseBlockDto {
    private Long id;
    private String date;
    private String time;
    private boolean isOpen;
    private int CurrentRegisterCount;
    private Long courseId;
}
