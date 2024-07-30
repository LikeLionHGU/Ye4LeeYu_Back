package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.dto.CourseBlockDto;
import com.example.ye4leeyu_back.application.dto.CourseDto;
import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.application.dto.TeacherDto;
import com.example.ye4leeyu_back.application.service.CourseBlockService;
import com.example.ye4leeyu_back.application.service.CourseService;
import com.example.ye4leeyu_back.application.service.StudentService;
import com.example.ye4leeyu_back.application.service.TeacherService;
import com.example.ye4leeyu_back.domain.entity.Course;
import com.example.ye4leeyu_back.presentation.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public RecommendCourseResponse getRecommendCourse(String location) {
        // 광고 강좌 가져오기
        List<CourseDto> adCourseDtoList = courseService.getAdCourseByLocation(location).stream().map(CourseDto::of).toList();
        // 추천 강좌 가져오기
        List<CourseDto> hotCourseDtoList = courseService.getHotCourseByLocation(location).stream().map(CourseDto::of).toList();

        List<CourseResponse> adCourseResponseList = adCourseDtoList.stream().map(courseDto ->
                CourseResponse.of(courseDto,
                        TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId())),
                        courseService.isCourseLiked(courseDto.getId(), memberId))).toList();

        List<CourseResponse> hotCourseResponseList = hotCourseDtoList.stream().map(courseDto ->
                CourseResponse.of(courseDto,
                        TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId())),
                        courseService.isCourseLiked(courseDto.getId(), memberId))).toList();

        return RecommendCourseResponse.of(adCourseResponseList, hotCourseResponseList);
    }

    public List<CourseResponse> searchCourse(String searchWord, String city, List<String> district, List<String> sportType, List<String> disabilityType, List<LocalDate> date, Integer price, Pageable pageable) {
        return courseService.getCourseByFilters(searchWord, city, district, sportType, disabilityType, date, price, pageable).stream().map(course ->
                CourseResponse.of(CourseDto.of(course),
                        TeacherDto.of(teacherService.getTeacher(course.getTeacher().getId())),
                        courseService.isCourseLiked(course.getId(), memberId))).toList();
    }


}
