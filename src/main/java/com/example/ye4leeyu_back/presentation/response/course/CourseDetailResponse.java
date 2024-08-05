package com.example.ye4leeyu_back.presentation.response.course;

import com.example.ye4leeyu_back.application.dto.CourseDto;
import com.example.ye4leeyu_back.presentation.response.PossibleDisabilityTypeResponse;
import com.example.ye4leeyu_back.presentation.response.TeacherResponse;
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
    private double latitude;
    private double longitude;
    private int duration;
    private TeacherResponse teacher;
    private List<CourseBlockResponse> courseBlock;

    public static CourseDetailResponse of(CourseDto courseDto, TeacherResponse teacherResponse, boolean isLike) {
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
                .latitude(courseDto.getLatitude())
                .longitude(courseDto.getLongitude())
                .duration(courseDto.getDuration())
                .teacher(teacherResponse)
                .courseBlock(courseDto.getCourseBlockList()
                        .stream()
                        .map(CourseBlockResponse::of)
                        .toList())
                .build();
    }
}
