package com.example.ye4leeyu_back.domain.repository.querydsl;

import com.example.ye4leeyu_back.domain.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepositoryCustom {

    Page<Course> findByFilters(String searchWord, String city, List<String> district, List<String> sportType, List<String> disabilityType, List<LocalDate> date, Integer highestPrice, Integer lowestPrice, Pageable pageable);
}
