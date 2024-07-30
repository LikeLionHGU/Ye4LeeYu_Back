package com.example.ye4leeyu_back.presentation.response;

import com.example.ye4leeyu_back.application.dto.CourseDto;
import com.example.ye4leeyu_back.application.dto.TeacherDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CourseResponse {
    private Long id;
    private String title;
    private String sportType;
    private List<PossibleDisabilityTypeResponse> possibleDisabilityType;
    private int price;
    private boolean isGroup;
    private int likeCount;
    private boolean isLike;
    private String location;
    private int duration;
    private String imageURL;
    private String teacherName;

    public static CourseResponse of(CourseDto courseDto, TeacherDto teacherDto, boolean isLike) {
        return CourseResponse.builder()
                .id(courseDto.getId())
                .title(courseDto.getTitle())
                .sportType(courseDto.getSportType())
                .possibleDisabilityType(courseDto.getPossibleDisabilityTypeList()
                        .stream()
                        .map(PossibleDisabilityTypeResponse::of)
                        .toList())
                .price(courseDto.getPrice())
                .isGroup(courseDto.getIsGroup())
                .likeCount(courseDto.getLikeCount())
                .isLike(isLike)
                .location(courseDto.getLocation())
                .duration(courseDto.getDuration())
                .imageURL(courseDto.getImageName())
                .teacherName(teacherDto.getName())
                .build();

    }
}
