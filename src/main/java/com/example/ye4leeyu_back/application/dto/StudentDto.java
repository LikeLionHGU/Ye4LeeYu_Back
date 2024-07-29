package com.example.ye4leeyu_back.application.dto;

import com.example.ye4leeyu_back.domain.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class StudentDto {
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
    private String disabilityType;
    private int disabilityLevel;
    private int level;
    private int finishedCourseCount;
    private String contactNumber;
    private String familyNumber;

    public static StudentDto of(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .bornYear(student.getBornYear())
                .bornMonth(student.getBornMonth())
                .bornDay(student.getBornDay())
                .sex(student.isSex())
                .nickname(student.getNickname())
                .description(student.getDescription())
                .imageName(student.getImageName())
                .role(student.getRole())
                .disabilityType(student.getDisabilityType())
                .disabilityLevel(student.getDisabilityLevel())
                .level(student.getLevel())
                .finishedCourseCount(student.getFinishedCourseCount())
                .contactNumber(student.getContactNumber())
                .familyNumber(student.getFamilyNumber())
                .build();
    }

}


