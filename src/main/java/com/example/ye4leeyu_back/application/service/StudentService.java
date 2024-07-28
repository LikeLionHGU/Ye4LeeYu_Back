package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public Student getstudent(Long memberId) {
        return studentRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }
}
