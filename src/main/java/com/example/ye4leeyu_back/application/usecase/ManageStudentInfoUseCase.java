package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.application.service.AuthService;
import com.example.ye4leeyu_back.application.service.StudentService;
import com.example.ye4leeyu_back.domain.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ManageStudentInfoUseCase {
    private final StudentService studentService;
    private final AuthService authService;

    public void updateStudentInfo(String kakaoId, StudentDto from) {
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        studentService.updateStudentInfo(student, from);
    }

    public String createStudent(MultipartFile profileImage, StudentDto from, String accessToken) {
        String kakaoId = authService.getKakaoId(accessToken);
        studentService.createStudent(profileImage, from, kakaoId);
        return authService.createJwtToken(kakaoId);
    }

    public String getKakaoAccessToken(String code) {
        return authService.getKakaoAccessToken(code);
    }
}
