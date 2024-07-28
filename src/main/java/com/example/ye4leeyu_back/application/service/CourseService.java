package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.application.dto.CourseDto;
import com.example.ye4leeyu_back.domain.entity.Course;
import com.example.ye4leeyu_back.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public Course getCourseAndCourseBlock(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    public boolean isCourseLiked(Long courseId, Long memberId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));
        return (course.getStudentLikeCourseList().stream()
                .anyMatch(studentLikeCourse -> studentLikeCourse.getStudent().getId().equals(memberId)));

    }
}
