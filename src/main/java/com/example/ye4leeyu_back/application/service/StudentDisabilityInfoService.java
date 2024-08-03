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
        // 기존 정보 삭제 후 새롭게 저장
        studentDisabilityInfoRepository.deleteAllByStudent(student);
        studentDisabilityInfoRepository.flush();
        createStudentDisabilityInfo(student, disabilityTypeList, disabilityLevelList);
    }
}
