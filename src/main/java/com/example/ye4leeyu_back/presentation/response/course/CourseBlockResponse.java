package com.example.ye4leeyu_back.presentation.response.course;

import com.example.ye4leeyu_back.application.dto.CourseBlockDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CourseBlockResponse {
    private Long id;
    private LocalDate date;
    private int takenHour;
    private int takenMinute;
    private boolean isOpen;
    private int currentRegisterCount;

    public static CourseBlockResponse of(CourseBlockDto courseBlockDto) {
        return CourseBlockResponse.builder()
                .id(courseBlockDto.getId())
                .date(courseBlockDto.getDate())
                .takenHour(courseBlockDto.getStartHour())
                .takenMinute(courseBlockDto.getStartMinute())
                .isOpen(courseBlockDto.isOpen())
                .currentRegisterCount(courseBlockDto.getCurrentRegisterCount())
                .build();
    }
}
