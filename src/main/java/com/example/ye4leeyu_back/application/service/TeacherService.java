package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.domain.entity.Teacher;
import com.example.ye4leeyu_back.domain.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public Teacher getTeacher(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
    }
}
