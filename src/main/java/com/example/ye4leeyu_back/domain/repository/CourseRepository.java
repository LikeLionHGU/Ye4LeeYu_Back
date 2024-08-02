package com.example.ye4leeyu_back.domain.repository;

import com.example.ye4leeyu_back.domain.entity.Course;
import com.example.ye4leeyu_back.domain.repository.querydsl.CourseRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryCustom {
    @Query("SELECT c FROM Course c WHERE c.isAd = true")
    List<Course> findAdCourse();

    List<Course> findCourseByLocationContaining(String location);

    Page<Course> findByFilters(String searchWord, String city, List<String> district, List<String> sportType, List<String> disabilityType, List<LocalDate> date, Integer price, Pageable pageable);

}
