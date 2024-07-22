package com.example.ye4leeyu_back.domain.repository;

import com.example.ye4leeyu_back.domain.entity.CourseBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseBlockRepository extends JpaRepository<CourseBlock, Long> {
}
