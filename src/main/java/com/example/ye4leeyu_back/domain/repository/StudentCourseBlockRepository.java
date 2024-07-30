package com.example.ye4leeyu_back.domain.repository;

import com.example.ye4leeyu_back.domain.entity.CourseBlock;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.domain.entity.StudentCourseBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseBlockRepository extends JpaRepository<StudentCourseBlock, Long> {
    List<StudentCourseBlock> findAllByStudent(Student student);

    boolean existsByStudentAndCourseBlock(Student student, CourseBlock courseBlock);
}
