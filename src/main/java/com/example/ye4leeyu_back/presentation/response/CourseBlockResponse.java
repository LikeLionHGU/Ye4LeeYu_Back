package com.example.ye4leeyu_back.presentation.response;

import com.example.ye4leeyu_back.application.dto.CourseBlockDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseBlockResponse {
    private Long id;
    private int heldYear;
    private int heldMonth;
    private int heldDay;
    private int takenHour;
    private int takenMinute;
    private boolean isOpen;
    private int currentRegisterCount;

    public static CourseBlockResponse of(CourseBlockDto courseBlockDto) {
        return CourseBlockResponse.builder()
                .id(courseBlockDto.getId())
                .heldYear(courseBlockDto.getHeldYear())
                .heldMonth(courseBlockDto.getHeldMonth())
                .heldDay(courseBlockDto.getHeldDay())
                .takenHour(courseBlockDto.getTakenHour())
                .takenMinute(courseBlockDto.getTakenMinute())
                .isOpen(courseBlockDto.isOpen())
                .currentRegisterCount(courseBlockDto.getCurrentRegisterCount())
                .build();
    }
}
