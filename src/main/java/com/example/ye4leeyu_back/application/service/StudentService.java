package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.domain.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student getStudentByKaKaoId(String kakaoId) {
        return studentRepository.findByKakaoId(kakaoId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    @Transactional
    public void updateStudentInfo(Student student, StudentDto from) {
        student.updateStudentInfo(from);
        studentRepository.save(student);
    }

    @Transactional
    public void createStudent(MultipartFile profileImage, StudentDto from, String kakaoId) {
        Student student = Student.createStudent(profileImage, from, kakaoId);
        if (studentRepository.existsByKakaoId(student.getKakaoId())) {
            return;
        }
        // TODO: 이미지 저장
        studentRepository.save(student);
    }
}
