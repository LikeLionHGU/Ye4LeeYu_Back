package com.example.ye4leeyu_back.application.dto;

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
}
