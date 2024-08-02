package com.example.ye4leeyu_back.presentation.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<String> disabilityTypeList;
    private List<Integer> disabilityLevelList;
    private int finishedCourseCount;
    private String contactNumber;
    private String familyNumber;
    private String code;
    private String address;
    private String detailAddress;
}