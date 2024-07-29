package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.domain.entity.Course;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.domain.entity.StudentLikeCourse;
import com.example.ye4leeyu_back.domain.repository.StudentLikeCourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentLikeCourseService {
    private final StudentLikeCourseRepository studentLikeCourseRepository;

    @Transactional
    public boolean likeCourse(Course course, Student student) {
        if (studentLikeCourseRepository.existsByCourseAndStudent(course, student)) {
            studentLikeCourseRepository.deleteByCourseAndStudent(course, student);
            return false;

        }
        else {
            StudentLikeCourse studentLikeCourse = StudentLikeCourse.from(course, student);
            studentLikeCourseRepository.save(studentLikeCourse);
            return true;
        }
    }
}
