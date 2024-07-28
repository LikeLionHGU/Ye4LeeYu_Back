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
public class CourseDetailResponse {
    private String imageURL;
    private String title;
    private String description;
    private String sportType;
    private List<PossibleDisabilityTypeResponse> possibleDisabilityType;
    private int price;
    private int maxPeople;
    private boolean isGroup;
    private int likeCount;
    private boolean isLike;
    private String location;
    private int duration;
    private TeacherResponse teacher;
    private List<CourseBlockResponse> courseBlock;

    public static CourseDetailResponse of(CourseDto courseDto, TeacherDto teacherDto, boolean isLike) {
        return CourseDetailResponse.builder()
                .imageURL(courseDto.getImageName())
                .title(courseDto.getTitle())
                .description(courseDto.getDescription())
                .sportType(courseDto.getSportType())
                .possibleDisabilityType(courseDto.getPossibleDisabilityTypeList()
                        .stream()
                        .map(PossibleDisabilityTypeResponse::of)
                        .toList())
                .price(courseDto.getPrice())
                .maxPeople(courseDto.getMaxPeople())
                .isGroup(courseDto.getIsGroup())
                .likeCount(courseDto.getLikeCount())
                .isLike(isLike)
                .location(courseDto.getLocation())
                .duration(courseDto.getDuration())
                .teacher(TeacherResponse.of(teacherDto))
                .courseBlock(courseDto.getCourseBlockList()
                        .stream()
                        .map(CourseBlockResponse::of)
                        .toList())
                .build();
    }
}
