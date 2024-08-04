package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.dto.CourseDto;
import com.example.ye4leeyu_back.application.dto.TeacherDto;
import com.example.ye4leeyu_back.application.service.CourseService;
import com.example.ye4leeyu_back.application.service.StudentCourseBlockService;
import com.example.ye4leeyu_back.application.service.StudentService;
import com.example.ye4leeyu_back.application.service.TeacherService;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.presentation.response.course.CourseResponse;
import com.example.ye4leeyu_back.presentation.response.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryUseCase {
    private final StudentService studentService;
    private final StudentCourseBlockService studentCourseBlockService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public HistoryResponse getHistory(String kakaoId) {
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        int level = student.getLevel();
        int totalCompletedCourse = studentCourseBlockService.getTotalCompletedCourse(student);

        return HistoryResponse.of(totalCompletedCourse, level);
    }

    public List<CourseResponse> getLikeCourse(String kakaoId) {
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        List<CourseDto> likeCourseDtoList = student.getStudentLikeCourseList().stream()
                .map(studentLikeCourse -> CourseDto.of(studentLikeCourse.getCourse())).toList();

        return likeCourseDtoList.stream().map(courseDto ->
                CourseResponse.of(courseDto,
                        TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId())),
                        courseService.isCourseLiked(courseDto.getId(), student.getId()))).toList();

    }

    public List<CourseResponse> getCourse(String kakaoId, boolean isCompleted) {
        Student student = studentService.getStudentByKaKaoId(kakaoId);

        List<CourseDto> courseDtoList = studentCourseBlockService.getCourseByIsCompleted(student, isCompleted).stream()
                .map(studentCourseBlock -> CourseDto.of(studentCourseBlock.getCourseBlock().getCourse())).toList();
        return courseDtoList.stream().map(courseDto ->
                CourseResponse.of(courseDto,
                        TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId())),
                        courseService.isCourseLiked(courseDto.getId(), student.getId()))).toList();
    }
}
