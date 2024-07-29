package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int discountRate;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

}
