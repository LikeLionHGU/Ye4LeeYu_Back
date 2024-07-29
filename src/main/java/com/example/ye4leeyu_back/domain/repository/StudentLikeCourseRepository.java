package com.example.ye4leeyu_back.domain.repository;

import com.example.ye4leeyu_back.domain.entity.Course;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.domain.entity.StudentLikeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentLikeCourseRepository extends JpaRepository<StudentLikeCourse, Long> {
    boolean existsByCourseAndStudent(Course course, Student student);

    void deleteByCourseAndStudent(Course course, Student student);
}
