package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int bornYear;
    private int bornMonth;
    private int bornDay;
    private boolean sex;
    private String nickname;
    private String description;
    private String imageName;
    private String role;
    private String disabilityType;
    private int disabilityLevel;
    private int level;
    private int finishedCourseCount;
    private String contactNumber;
    private String familyNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentLikeCourse> studentLikeCourseList = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentCourseBlock> studentCourseBlockList = new ArrayList<>();
}
