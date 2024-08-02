package com.example.ye4leeyu_back.application.dto;

import com.example.ye4leeyu_back.domain.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TeacherDto {
    private Long id;
    private String name;
    private int bornYear;
    private int bornMonth;
    private int bornDay;
    private boolean sex;
    private String nickname;
    private String description;
    private String imageName;
    private String role;
    private float score;
    private int reviewCount;
    private String career;
    private String contactNumber;

    List<CourseDto> courseList;

    public static TeacherDto of(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .bornYear(teacher.getBornYear())
                .bornMonth(teacher.getBornMonth())
                .bornDay(teacher.getBornDay())
                .sex(teacher.isSex())
                .nickname(teacher.getNickname())
                .description(teacher.getDescription())
                .imageName(teacher.getImageName())
                .role(teacher.getRole())
                .score(teacher.getScore())
                .reviewCount(teacher.getReviewCount())
                .career(teacher.getCareer())
                .contactNumber(teacher.getContactNumber())
                .courseList(teacher.getCourseList()
                        .stream()
                        .map(CourseDto::of)
                        .toList())
                .build();
    }

}
