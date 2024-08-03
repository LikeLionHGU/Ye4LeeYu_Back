package com.example.ye4leeyu_back.presentation.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Data
public class UpdateStudentInfoRequest {
    private String nickname;
    private String description;
    private String disabilityType;
    private List<String> disabilityTypeList;
    private List<Integer> disabilityLevelList;
    private String contactNumber;
    private String familyNumber;
    private String address;
    private String detailAddress;
}
