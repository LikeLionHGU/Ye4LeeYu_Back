package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String sportType;
    private String location;
    private int price;
    private int maxPeople;
    private String description;
    private String imageName;
    private boolean isGroup;
    private boolean isClose;
    private boolean isAd;
    private int duration;
    private int likeCount;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseBlock> courseBlockList = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<PossibleDisabilityType> possibleDisabilityTypeList = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<StudentLikeCourse> studentLikeCourseList = new ArrayList<>();

    public void updateLikeCount(boolean isLike) {
        if (isLike)
            this.likeCount++;
        else
            this.likeCount--;
    }
}
