package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.domain.entity.CourseBlock;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.domain.entity.StudentCourseBlock;
import com.example.ye4leeyu_back.domain.repository.StudentCourseBlockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public int getTotalCompletedCourse(Student student) {
        List<StudentCourseBlock> studentCourseBlocks = studentCourseBlockRepository.findAllByStudent(student);

        return (int) studentCourseBlocks.stream()
                .filter(StudentCourseBlock::isCompleted)
                .count();
    }

    public List<StudentCourseBlock> getCourseByIsCompleted(Student student, boolean isCompleted) {
        return studentCourseBlockRepository.findAllByStudent(student).stream()
                .filter(studentCourseBlock -> studentCourseBlock.isCompleted() == isCompleted)
                .toList();
    }
}
