package com.example.ye4leeyu_back.presentation.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class UpdateStudentInfoRequest {
    private String nickname;
    private String description;
    private String disabilityType;
    private int disabilityLevel;
    private String contactNumber;
    private String familyNumber;
}
