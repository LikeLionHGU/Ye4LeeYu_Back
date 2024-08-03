package com.example.ye4leeyu_back.domain.repository;

import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.domain.entity.StudentDisabilityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDisabilityInfoRepository extends JpaRepository<StudentDisabilityInfo, Long> {
    void deleteAllByStudent(Student student);
}
