package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.service.CourseBlockService;
import com.example.ye4leeyu_back.application.service.StudentCourseBlockService;
import com.example.ye4leeyu_back.application.service.StudentService;
import com.example.ye4leeyu_back.domain.entity.CourseBlock;
import com.example.ye4leeyu_back.domain.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageCourseUseCase {
    private final StudentService studentService;
    private final CourseBlockService courseBlockService;
    private final StudentCourseBlockService studentCourseBlockService;

    private final Long memberId; // temporary member id

    public void createCourse(Long courseBlockId) {
        Student student = studentService.getstudent(memberId);
        CourseBlock courseBlock = courseBlockService.getCourseBlock(courseBlockId);
        studentCourseBlockService.createStudentCourseBlock(student, courseBlock);
    }
}