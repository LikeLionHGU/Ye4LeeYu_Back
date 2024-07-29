package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public static StudentLikeCourse from(Course course, Student student) {
        return StudentLikeCourse.builder()
                .course(course)
                .student(student)
                .build();
    }
}
