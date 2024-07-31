package com.example.ye4leeyu_back.presentation.response;

import com.example.ye4leeyu_back.application.dto.TeacherDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeacherResponse {
    private Long id;
    private String name;
    private boolean sex;
    private String description;
    private String imageURL;
    private int score;
    private int scorePersonCount;
    private String career;
    private String contactNumber;

    public static TeacherResponse of(TeacherDto teacher) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .sex(teacher.isSex())
                .description(teacher.getDescription())
                .imageURL(teacher.getImageName())
                .score(teacher.getScore())
                .scorePersonCount(teacher.getScorePersonCount())
                .career(teacher.getCareer())
                .contactNumber(teacher.getContactNumber())
                .build();
    }
}
