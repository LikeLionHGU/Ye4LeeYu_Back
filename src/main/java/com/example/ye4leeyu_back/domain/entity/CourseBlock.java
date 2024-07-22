package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;

@Entity
public class CourseBlock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String time;
    private boolean isOpen;
    private int CurrentRegisterCount;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
}
