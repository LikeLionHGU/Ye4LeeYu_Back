package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PossibleDisabilityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String disabilityType;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
}
