package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.application.service.AuthService;
import com.example.ye4leeyu_back.application.service.StudentDisabilityInfoService;
import com.example.ye4leeyu_back.application.service.StudentService;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.presentation.response.ProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageStudentInfoUseCase {
    private final StudentService studentService;
    private final AuthService authService;
    private final StudentDisabilityInfoService studentDisabilityInfoService;

    public void updateStudentInfo(String kakaoId, StudentDto from) {
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        studentService.updateStudentInfo(student, from);
        studentDisabilityInfoService.updateStudentDisabilityInfo(student, from.getDisabilityTypeList(), from.getDisabilityLevelList());
    }

    public String createStudent(StudentDto from, String accessToken) {
        String kakaoId = authService.getKakaoId(accessToken);
        if (studentService.isExistStudent(kakaoId)) {
            throw new IllegalArgumentException("Already signed up");
        }
        studentService.createStudent(from, kakaoId);
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        studentDisabilityInfoService.createStudentDisabilityInfo(student, from.getDisabilityTypeList(), from.getDisabilityLevelList());
        return authService.createJwtToken(kakaoId);
    }

    public ProfileResponse getStudentInfo(String kakaoId) {
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        return ProfileResponse.of(StudentDto.of(student));
    }

    public String getKakaoAccessToken(String code) {
        return authService.getKakaoAccessToken(code);
    }

    public String getJwtTokenByKaKaoId(String kakaoId){
        return authService.createJwtToken(kakaoId);
    }

    public boolean isExistStudent(String kakaoId) {
        return studentService.isExistStudent(kakaoId);
    }

    public String getKakaoId(String accessToken) {
        return authService.getKakaoId(accessToken);
    }
}
