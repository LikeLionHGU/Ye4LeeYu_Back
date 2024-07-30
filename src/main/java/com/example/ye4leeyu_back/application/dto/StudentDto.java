package com.example.ye4leeyu_back.application.dto;

import com.example.ye4leeyu_back.domain.entity.Coupon;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.presentation.request.UpdateStudentInfoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<CouponDto> couponList;

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
                .couponList(student.getCouponList()
                        .stream()
                        .map(CouponDto::of)
                        .toList())
                .build();
    }

    public static StudentDto from(UpdateStudentInfoRequest request) {
        return StudentDto.builder()
                .nickname(request.getNickname())
                .description(request.getDescription())
                .disabilityType(request.getDisabilityType())
                .disabilityLevel(request.getDisabilityLevel())
                .contactNumber(request.getContactNumber())
                .familyNumber(request.getFamilyNumber())
                .build();
    }
}


