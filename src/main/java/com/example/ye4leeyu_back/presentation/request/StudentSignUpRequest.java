package com.example.ye4leeyu_back.presentation.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class StudentSignUpRequest {
    private String name;
    private int bornYear;
    private int bornMonth;
    private int bornDay;
    private boolean sex;
    private String nickname;
    private String description;
    private String disabilityType;
    private int disabilityLevel;
    private int finishedCourseCount;
    private String contactNumber;
    private String familyNumber;
}