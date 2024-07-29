package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.dto.CourseBlockDto;
import com.example.ye4leeyu_back.application.dto.CourseDto;
import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.application.dto.TeacherDto;
import com.example.ye4leeyu_back.application.service.CourseBlockService;
import com.example.ye4leeyu_back.application.service.CourseService;
import com.example.ye4leeyu_back.application.service.StudentService;
import com.example.ye4leeyu_back.application.service.TeacherService;
import com.example.ye4leeyu_back.presentation.response.CourseDetailResponse;
import com.example.ye4leeyu_back.presentation.response.PurchaseInfoResponse;
import com.example.ye4leeyu_back.presentation.response.StudentInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CourseSearchAndLookUseCase {
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final CourseBlockService courseBlockService;

    private final Long memberId; // temporary member id

    public CourseDetailResponse getCourseDetail(Long courseId) {
        CourseDto courseDto = CourseDto.of(courseService.getCourseAndCourseBlock(courseId));
        TeacherDto teacherDto = TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId()));
        boolean isLiked = courseService.isCourseLiked(courseId, memberId);
        return CourseDetailResponse.of(courseDto, teacherDto, isLiked);
    }

    public PurchaseInfoResponse getPurchaseInfo(Long courseId, Long courseBlockId) {
        StudentDto studentDto = StudentDto.of(studentService.getStudent(memberId));

        CourseDto courseDto = CourseDto.of(courseService.getCourseAndCourseBlock(courseId));
        courseDto.setCourseBlockList(new ArrayList<>()); // 빈 리스트로 초기화
        courseDto.getCourseBlockList().add(CourseBlockDto.of(courseBlockService.getCourseBlock(courseBlockId))); // 해당하는 courseBlock만 추가

        TeacherDto teacherDto = TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId()));

        boolean isLiked = courseService.isCourseLiked(courseId, memberId);
        return PurchaseInfoResponse.of(StudentInfoResponse.of(studentDto), CourseDetailResponse.of(courseDto, teacherDto, isLiked));
    }


}
