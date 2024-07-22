package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;

@Entity
public class StudentLikeCourse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
}
