package com.example.ye4leeyu_back.application.dto;

import com.example.ye4leeyu_back.domain.entity.Course;
import com.example.ye4leeyu_back.domain.entity.CourseBlock;
import com.example.ye4leeyu_back.domain.entity.PossibleDisabilityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<CourseBlockDto> courseBlockList;
    private List<PossibleDisabilityTypeDto> possibleDisabilityTypeList;

    public static CourseDto of(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .sportType(course.getSportType())
                .location(course.getLocation())
                .price(course.getPrice())
                .maxPeople(course.getMaxPeople())
                .description(course.getDescription())
                .imageName(course.getImageName())
                .isGroup(course.isGroup())
                .isClose(course.isClose())
                .duration(course.getDuration())
                .likeCount(course.getLikeCount())
                .teacherId(course.getTeacher().getId())
                .courseBlockList(course.getCourseBlockList()
                        .stream()
                        .map(CourseBlockDto::of)
                        .toList())
                .possibleDisabilityTypeList(course.getPossibleDisabilityTypeList()
                        .stream()
                        .map(PossibleDisabilityTypeDto::of)
                        .toList())
                .build();
    }

}
