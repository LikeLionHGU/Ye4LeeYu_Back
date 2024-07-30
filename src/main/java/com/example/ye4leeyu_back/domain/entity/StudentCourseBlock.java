package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public static StudentCourseBlock from(Student student, CourseBlock courseBlock) {
        return StudentCourseBlock.builder()
                .isCompleted(false)
                .student(student)
                .courseBlock(courseBlock)
                .build();
    }
}
