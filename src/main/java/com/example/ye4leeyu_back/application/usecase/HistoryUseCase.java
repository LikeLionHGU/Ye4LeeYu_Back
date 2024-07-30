package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.dto.CourseDto;
import com.example.ye4leeyu_back.application.dto.TeacherDto;
import com.example.ye4leeyu_back.application.service.CourseService;
import com.example.ye4leeyu_back.application.service.StudentCourseBlockService;
import com.example.ye4leeyu_back.application.service.StudentService;
import com.example.ye4leeyu_back.application.service.TeacherService;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.presentation.response.CourseResponse;
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

    private final Long memberId; // temporary member id

    public HistoryResponse getHistory() {
        Student student = studentService.getStudent(memberId);
        int level = student.getLevel();
        int totalCompletedCourse = studentCourseBlockService.getTotalCompletedCourse(student);

        return HistoryResponse.of(totalCompletedCourse, level);
    }

    public List<CourseResponse> getLikeCourse() {
        Student student = studentService.getStudent(memberId);
        List<CourseDto> likeCourseDtoList = student.getStudentLikeCourseList().stream()
                .map(studentLikeCourse -> CourseDto.of(studentLikeCourse.getCourse())).toList();

        return likeCourseDtoList.stream().map(courseDto ->
                CourseResponse.of(courseDto,
                        TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId())),
                        courseService.isCourseLiked(courseDto.getId(), memberId))).toList();

    }

    public List<CourseResponse> getCourse(boolean isCompleted) {
        Student student = studentService.getStudent(memberId);

        List<CourseDto> courseDtoList = studentCourseBlockService.getCourseByIsCompleted(student, isCompleted).stream()
                .map(studentCourseBlock -> CourseDto.of(studentCourseBlock.getCourseBlock().getCourse())).toList();
        return courseDtoList.stream().map(courseDto ->
                CourseResponse.of(courseDto,
                        TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId())),
                        courseService.isCourseLiked(courseDto.getId(), memberId))).toList();
    }
}
