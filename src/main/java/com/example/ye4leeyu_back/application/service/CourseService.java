package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.domain.entity.Course;
import com.example.ye4leeyu_back.domain.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

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

    public int getLikeCount(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));

        return course.getLikeCount();
    }

    @Transactional
    public void updateLikeCount(Long courseId, boolean isLike) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));
        course.updateLikeCount(isLike);
        courseRepository.save(course);
    }

    public List<Course> getAdCourseByLocation(String location) {
        return getCourseByLocation(location).stream().filter(Course::isAd).toList();
    }

    public List<Course> getHotCourseByLocation(String location) {
        List<Course> courses = getCourseByLocation(location);
        courses.sort(Comparator.comparing(Course::getLikeCount).reversed());

        return courses;
    }

    public List<Course> getCourseByLocation(String location) {
        List<Course> courses = courseRepository.findCourseByLocationContaining(location);
        if (courses.isEmpty()) {
            throw new IllegalArgumentException("No course");
        }
        return courses;
    }

    public List<Course> getCourseByFilters(String searchWord, String city, List<String> district, List<String> sportType, List<String> disabilityType, List<LocalDate> date, Integer highestPrice, Integer lowestPrice, Pageable pageable) {
        Page<Course> courses = courseRepository.findByFilters(searchWord, city, district, sportType, disabilityType, date, highestPrice, lowestPrice, pageable);

        return courses.getContent();
    }
}
