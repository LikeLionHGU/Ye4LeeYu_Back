package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;

@Entity
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String sportType;
    private String location;
    private String possibleDisabilityType;
    private int price;
    private int maxPeople;
    private String description;
    private String imageName;
    private boolean isGroup;
    private boolean isClose;
    private int duration;
    private int likeCount;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;
}
