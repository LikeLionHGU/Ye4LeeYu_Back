package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
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
    private int score;
    private String career;
    private int contactNumber;
}
