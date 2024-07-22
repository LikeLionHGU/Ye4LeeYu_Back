package com.example.ye4leeyu_back.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CourseDto {
    private Long id;
    private String title;
    private String sportType;
    private String location;
    private String possibleDisabilityType;
    private int price;
    private int maxPeople;
    private String description;
    private String imageName;
    private Boolean isGroup;
    private Boolean isClose;
    private int duration;
    private int likeCount;
    private Long teacherId;
}
