package com.example.ye4leeyu_back.presentation.response;

import com.example.ye4leeyu_back.application.dto.StudentDto;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Data
@Builder
public class ProfileResponse {
    private String name;
    private int bornYear;
    private int bornMonth;
    private int bornDay;
    private boolean sex;
    private String nickname;
    private String description;
    private List<String> disabilityTypeList;
    private List<Integer> disabilityLevelList;
    private String contactNumber;
    private String familyNumber;
    private String address;
    private String detailAddress;

    public static ProfileResponse of(StudentDto studentDto) {
        return ProfileResponse.builder()
                .name(studentDto.getName())
                .bornYear(studentDto.getBornYear())
                .bornMonth(studentDto.getBornMonth())
                .bornDay(studentDto.getBornDay())
                .sex(studentDto.isSex())
                .nickname(studentDto.getNickname())
                .description(studentDto.getDescription())
                .disabilityTypeList(studentDto.getDisabilityTypeList())
                .disabilityLevelList(studentDto.getDisabilityLevelList())
                .contactNumber(studentDto.getContactNumber())
                .familyNumber(studentDto.getFamilyNumber())
                .address(studentDto.getAddress())
                .detailAddress(studentDto.getDetailAddress())
                .build();
    }
}


