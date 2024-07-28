package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.domain.entity.CourseBlock;
import com.example.ye4leeyu_back.domain.repository.CourseBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseBlockService {
    private final CourseBlockRepository courseBlockRepository;

    public CourseBlock getCourseBlock(Long courseBlockId) {
        return courseBlockRepository.findById(courseBlockId).orElseThrow(() -> new IllegalArgumentException("CourseBlock not found"));
    }
}
