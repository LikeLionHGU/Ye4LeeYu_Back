package com.example.ye4leeyu_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDisabilityInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String disabilityType;
    private int disabilityLevel;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    public static List<StudentDisabilityInfo> createStudentDisabilityInfo(Student student, List<String> disabilityTypeList, List<Integer> disabilityLevelList) {
        for (int i = 0; i < disabilityTypeList.size(); i++) {
            StudentDisabilityInfo studentDisabilityInfo = StudentDisabilityInfo.builder()
                    .disabilityType(disabilityTypeList.get(i))
                    .disabilityLevel(disabilityLevelList.get(i))
                    .student(student)
                    .build();
            student.getStudentDisabilityInfoList().add(studentDisabilityInfo);
        }
        return student.getStudentDisabilityInfoList();
    }

    public void updateStudentDisabilityInfo(String disabilityType, Integer disabilityLevel) {
        this.disabilityType = disabilityType;
        this.disabilityLevel = disabilityLevel;
    }
}
