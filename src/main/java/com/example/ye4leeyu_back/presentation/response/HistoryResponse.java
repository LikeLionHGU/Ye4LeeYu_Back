package com.example.ye4leeyu_back.presentation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HistoryResponse {
    private int totalCompletedCourse;
    private int level;

    public static HistoryResponse of(int totalCompletedCourse, int level) {
        return HistoryResponse.builder()
                .totalCompletedCourse(totalCompletedCourse)
                .level(level)
                .build();
    }
}
