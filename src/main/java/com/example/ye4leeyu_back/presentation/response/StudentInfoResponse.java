package com.example.ye4leeyu_back.presentation.response;

import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.domain.entity.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class StudentInfoResponse {

    private String name;
    private int bornYear;
    private int bornMonth;
    private int bornDay;
    private String contactNumber;
    private List<CouponResponse> couponList;

    public static StudentInfoResponse of(StudentDto studentDto) {
        return StudentInfoResponse.builder()
                .name(studentDto.getName())
                .bornYear(studentDto.getBornYear())
                .bornMonth(studentDto.getBornMonth())
                .bornDay(studentDto.getBornDay())
                .contactNumber(studentDto.getContactNumber())
                .couponList(studentDto.getCouponList()
                        .stream()
                        .map(CouponResponse::of)
                        .toList())
                .build();
    }

}
