package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.dto.CourseBlockDto;
import com.example.ye4leeyu_back.application.dto.CourseDto;
import com.example.ye4leeyu_back.application.dto.StudentDto;
import com.example.ye4leeyu_back.application.dto.TeacherDto;
import com.example.ye4leeyu_back.application.service.CourseBlockService;
import com.example.ye4leeyu_back.application.service.CourseService;
import com.example.ye4leeyu_back.application.service.StudentService;
import com.example.ye4leeyu_back.application.service.TeacherService;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.presentation.response.*;
import com.example.ye4leeyu_back.presentation.response.course.CourseDetailResponse;
import com.example.ye4leeyu_back.presentation.response.course.CourseResponse;
import com.example.ye4leeyu_back.presentation.response.course.RecommendCourseResponse;
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

    public CourseDetailResponse getCourseDetail(String kakaoId, Long courseId) {
        CourseDto courseDto = CourseDto.of(courseService.getCourseAndCourseBlock(courseId));
        TeacherDto teacherDto = TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId()));
        if (kakaoId == null) {
            TeacherResponse teacherResponse = TeacherResponse.of(teacherDto, teacherDto.getCourseList().stream().map(dto -> CourseResponse.of(dto, teacherDto, false)).toList());
            return CourseDetailResponse.of(courseDto, teacherResponse, false);
        }
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        boolean isLiked = courseService.isCourseLiked(courseId, student.getId());

        TeacherResponse teacherResponse = TeacherResponse.of(teacherDto, teacherDto.getCourseList().stream().map(dto -> CourseResponse.of(dto, teacherDto, courseService.isCourseLiked(courseId, student.getId()))).toList());
        return CourseDetailResponse.of(courseDto, teacherResponse, isLiked);
    }

    public PurchaseInfoResponse getPurchaseInfo(String kakaoId, Long courseId, Long courseBlockId) {
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        StudentDto studentDto = StudentDto.of(student);

        CourseDto courseDto = CourseDto.of(courseService.getCourseAndCourseBlock(courseId));
        courseDto.setCourseBlockList(new ArrayList<>()); // 빈 리스트로 초기화
        courseDto.getCourseBlockList().add(CourseBlockDto.of(courseBlockService.getCourseBlock(courseBlockId))); // 해당하는 courseBlock만 추가

        TeacherDto teacherDto = TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId()));

        TeacherResponse teacherResponse = TeacherResponse.of(teacherDto, teacherDto.getCourseList().stream().map(dto -> CourseResponse.of(dto, teacherDto, courseService.isCourseLiked(courseId, student.getId()))).toList());

        boolean isLiked = courseService.isCourseLiked(courseId, student.getId());
        return PurchaseInfoResponse.of(StudentInfoResponse.of(studentDto), CourseDetailResponse.of(courseDto, teacherResponse, isLiked));
    }

    public RecommendCourseResponse getRecommendCourse(String kakaoId, String location) {
        // 광고 강좌 가져오기
        List<CourseDto> adCourseDtoList = courseService.getAdCourseByLocation(location).stream().map(CourseDto::of).toList();
        // 추천 강좌 가져오기
        List<CourseDto> hotCourseDtoList = courseService.getHotCourseByLocation(location).stream().map(CourseDto::of).toList();

        if (kakaoId == null) {
            List<CourseResponse> adCourseResponseList = adCourseDtoList.stream().map(courseDto ->
                    CourseResponse.of(courseDto,
                            TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId())),
                            false)).toList();

            List<CourseResponse> hotCourseResponseList = hotCourseDtoList.stream().map(courseDto ->
                    CourseResponse.of(courseDto,
                            TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId())),
                            false)).toList();

            return RecommendCourseResponse.of(adCourseResponseList, hotCourseResponseList);
        }

        Student student = studentService.getStudentByKaKaoId(kakaoId);
        // 광고 강좌 가져오기
        List<CourseResponse> adCourseResponseList = adCourseDtoList.stream().map(courseDto ->
                CourseResponse.of(courseDto,
                        TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId())),
                        courseService.isCourseLiked(courseDto.getId(), student.getId()))).toList();

        List<CourseResponse> hotCourseResponseList = hotCourseDtoList.stream().map(courseDto ->
                CourseResponse.of(courseDto,
                        TeacherDto.of(teacherService.getTeacher(courseDto.getTeacherId())),
                        courseService.isCourseLiked(courseDto.getId(), student.getId()))).toList();

        return RecommendCourseResponse.of(adCourseResponseList, hotCourseResponseList);
    }

    public List<CourseResponse> searchCourse(String kakaoId, String searchWord, String city, List<String> district, List<String> sportType, List<String> disabilityType, List<LocalDate> date, Integer highestPrice, Integer lowestPrice, Boolean onlyGroup, Boolean onlyIndividual, Pageable pageable) {
        if(kakaoId == null) {
            return courseService.getCourseByFilters(searchWord, city, district, sportType, disabilityType, date, highestPrice, lowestPrice, onlyGroup, onlyIndividual, pageable).stream().map(course ->
                    CourseResponse.of(CourseDto.of(course),
                            TeacherDto.of(teacherService.getTeacher(course.getTeacher().getId())),
                            false)).toList();
        }
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        return courseService.getCourseByFilters(searchWord, city, district, sportType, disabilityType, date, highestPrice, lowestPrice, onlyGroup, onlyIndividual, pageable).stream().map(course ->
                CourseResponse.of(CourseDto.of(course),
                        TeacherDto.of(teacherService.getTeacher(course.getTeacher().getId())),
                        courseService.isCourseLiked(course.getId(), student.getId()))).toList();
    }


}
