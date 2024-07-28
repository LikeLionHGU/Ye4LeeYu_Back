package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.domain.entity.CourseBlock;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.domain.entity.StudentCourseBlock;
import com.example.ye4leeyu_back.domain.repository.StudentCourseBlockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentCourseBlockService {
    private final StudentCourseBlockRepository studentCourseBlockRepository;

    @Transactional
    public void createStudentCourseBlock(Student student, CourseBlock courseBlock) {
        if (studentCourseBlockRepository.existsByStudent(student)) {
            throw new IllegalArgumentException("Student already exists");
        }

        StudentCourseBlock studentCourseBlock = StudentCourseBlock.from(student, courseBlock);
        studentCourseBlockRepository.save(studentCourseBlock);
    }
}
