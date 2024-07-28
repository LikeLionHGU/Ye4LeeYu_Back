package com.example.ye4leeyu_back.application.dto;

import com.example.ye4leeyu_back.domain.entity.CourseBlock;
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
    private int heldYear;
    private int heldMonth;
    private int heldDay;
    private int takenHour;
    private int takenMinute;
    private boolean isOpen;
    private int CurrentRegisterCount;
    private Long courseId;

    public static CourseBlockDto of(CourseBlock courseBlock) {
        return CourseBlockDto.builder()
                .id(courseBlock.getId())
                .heldYear(courseBlock.getHeldYear())
                .heldMonth(courseBlock.getHeldMonth())
                .heldDay(courseBlock.getHeldDay())
                .takenHour(courseBlock.getTakenHour())
                .takenMinute(courseBlock.getTakenMinute())
                .isOpen(courseBlock.isOpen())
                .CurrentRegisterCount(courseBlock.getCurrentRegisterCount())
                .courseId(courseBlock.getCourse().getId())
                .build();
    }
}
