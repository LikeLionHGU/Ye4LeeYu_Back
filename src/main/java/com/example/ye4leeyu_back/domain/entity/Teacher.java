package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Teacher extends BaseEntity {
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
    private float score;
    private int reviewCount;
    private int studentCount;
    private String career;
    private String contactNumber;

    @OneToMany
    @JoinColumn(name = "teacherId")
    private List<Course> courseList = new ArrayList<>();
}
