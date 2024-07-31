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

    private final Long memberId; // temporary member id

    public void updateStudentInfo(StudentDto from) {
        Student student = studentService.getStudent(memberId);
        studentService.updateStudentInfo(student, from);
    }

    public void createStudent(MultipartFile profileImage, StudentDto from) {
        studentService.createStudent(profileImage, from);
    }

    public String getKakaoAccessToken(String code) {
        return authService.getKakaoAccessToken(code);
    }

    public String kakaoLogin(String accessToken) {
        return authService.kakaoLogin(accessToken);
    }
}
