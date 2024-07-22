package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;

@Entity
public class StudentCourseBlock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courseBlockId")
    private CourseBlock courseBlock;
}
