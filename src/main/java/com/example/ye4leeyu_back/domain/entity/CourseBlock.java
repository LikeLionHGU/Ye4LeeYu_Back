package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseBlock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private int takenHour;
    private int takenMinute;
    private boolean isOpen;
    private int CurrentRegisterCount;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @OneToMany(mappedBy = "courseBlock", cascade = CascadeType.ALL)
    private List<StudentCourseBlock> studentCourseBlockList = new ArrayList<>();


}
