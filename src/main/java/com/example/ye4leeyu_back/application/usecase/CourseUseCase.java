package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.dto.CourseDto;
import com.example.ye4leeyu_back.application.dto.TeacherDto;
import com.example.ye4leeyu_back.application.service.CourseService;
import com.example.ye4leeyu_back.application.service.TeacherService;
import com.example.ye4leeyu_back.presentation.response.CourseDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseUseCase {
    private final CourseService courseService;
    private final TeacherService teacherService;

    private final Long memberId; // temporary member id

    public CourseDetailResponse getCourseDetail(Long courseId) {
        CourseDto courseDto = CourseDto.of(courseService.getCourseAndCourseBlock(courseId));
        TeacherDto teacherDto = TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId()));
        boolean isLiked = courseService.isCourseLiked(courseId, memberId);
        return CourseDetailResponse.of(courseDto, teacherDto, isLiked);
    }
}
