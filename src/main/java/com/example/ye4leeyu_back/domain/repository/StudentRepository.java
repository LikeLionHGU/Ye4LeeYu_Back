package com.example.ye4leeyu_back.domain.repository;

import com.example.ye4leeyu_back.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByKakaoId(String kakaoId);

    Optional<Student> findByKakaoId(String kakaoId);
}
