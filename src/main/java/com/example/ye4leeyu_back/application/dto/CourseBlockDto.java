package com.example.ye4leeyu_back.application.dto;

import com.example.ye4leeyu_back.domain.entity.CourseBlock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CourseBlockDto {
    private Long id;
    private LocalDate date;
    private int startHour;
    private int startMinute;
    private boolean isOpen;
    private int CurrentRegisterCount;
    private Long courseId;

    public static CourseBlockDto of(CourseBlock courseBlock) {
        return CourseBlockDto.builder()
                .id(courseBlock.getId())
                .date(courseBlock.getDate())
                .startHour(courseBlock.getStartHour())
                .startMinute(courseBlock.getStartMinute())
                .isOpen(courseBlock.isOpen())
                .CurrentRegisterCount(courseBlock.getCurrentRegisterCount())
                .courseId(courseBlock.getCourse().getId())
                .build();
    }
}
