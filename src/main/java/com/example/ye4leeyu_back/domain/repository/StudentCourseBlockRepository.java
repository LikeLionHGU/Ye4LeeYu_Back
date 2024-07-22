package com.example.ye4leeyu_back.domain.repository;

import com.example.ye4leeyu_back.domain.entity.StudentCourseBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseBlockRepository extends JpaRepository<StudentCourseBlock, Long> {
}
