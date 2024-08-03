package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.domain.entity.StudentDisabilityInfo;
import com.example.ye4leeyu_back.domain.repository.StudentDisabilityInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentDisabilityInfoService {
    private final StudentDisabilityInfoRepository studentDisabilityInfoRepository;

    @Transactional
    public void createStudentDisabilityInfo(Student student, List<String> disabilityTypeList, List<Integer> disabilityLevelList) {
        List<StudentDisabilityInfo> studentDisabilityInfoList = StudentDisabilityInfo.createStudentDisabilityInfo(student, disabilityTypeList, disabilityLevelList);
        studentDisabilityInfoRepository.saveAll(studentDisabilityInfoList);
    }

    @Transactional
    public void updateStudentDisabilityInfo(Student student, List<String> disabilityTypeList, List<Integer> disabilityLevelList) {
        List<StudentDisabilityInfo> studentDisabilityInfoList = student.getStudentDisabilityInfoList();
        System.out.println("studentDisabilityInfoList.size() = " + studentDisabilityInfoList.size() + "kakaoId = " + student.getKakaoId());
        for (int i = 0; i < studentDisabilityInfoList.size(); i++) {
            StudentDisabilityInfo studentDisabilityInfo = studentDisabilityInfoList.get(i);
            studentDisabilityInfo.updateStudentDisabilityInfo(disabilityTypeList.get(i), disabilityLevelList.get(i));
        }
    }
}
