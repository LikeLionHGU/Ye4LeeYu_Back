package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.service.*;
import com.example.ye4leeyu_back.domain.entity.Course;
import com.example.ye4leeyu_back.domain.entity.CourseBlock;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.presentation.response.LikeCountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageCourseUseCase {
    private final StudentService studentService;
    private final CourseBlockService courseBlockService;
    private final StudentCourseBlockService studentCourseBlockService;
    private final CourseService courseService;
    private final StudentLikeCourseService studentLikeCourseService;

    public void createCourse(String kakaoId, Long courseBlockId) {
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        CourseBlock courseBlock = courseBlockService.getCourseBlock(courseBlockId);
        studentCourseBlockService.createStudentCourseBlock(student, courseBlock);
    }

    public LikeCountResponse likeCourse(String kakaoId, Long courseId) {
        Course course = courseService.getCourseAndCourseBlock(courseId);
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        boolean isLike = studentLikeCourseService.likeCourse(course, student);
        courseService.updateLikeCount(courseId, isLike);
        int likeCount = courseService.getLikeCount(courseId);
        return LikeCountResponse.of(likeCount);
    }
}