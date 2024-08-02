package com.example.ye4leeyu_back.presentation.response;

import com.example.ye4leeyu_back.application.dto.TeacherDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TeacherResponse {
    private Long id;
    private String name;
    private boolean sex;
    private String description;
    private String imageURL;
    private float score;
    private int reviewCount;
    private String career;
    private String contactNumber;
    private List<CourseResponse> courseList;

    public static TeacherResponse of(TeacherDto teacher, List<CourseResponse> courseList) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .sex(teacher.isSex())
                .description(teacher.getDescription())
                .imageURL(teacher.getImageName())
                .score(teacher.getScore())
                .reviewCount(teacher.getReviewCount())
                .career(teacher.getCareer())
                .contactNumber(teacher.getContactNumber())
                .courseList(courseList)
                .build();
    }
}
