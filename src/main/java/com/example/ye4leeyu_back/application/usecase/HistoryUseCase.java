package com.example.ye4leeyu_back.application.usecase;

import com.example.ye4leeyu_back.application.service.StudentCourseBlockService;
import com.example.ye4leeyu_back.application.service.StudentService;
import com.example.ye4leeyu_back.domain.entity.Student;
import com.example.ye4leeyu_back.presentation.response.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryUseCase {
    private final StudentService studentService;
    private final StudentCourseBlockService studentCourseBlockService;

    private final Long memberId; // temporary member id

    public HistoryResponse getHistory() {
        Student student = studentService.getstudent(memberId);
        int level = student.getLevel();
        int totalCompletedCourse = studentCourseBlockService.getTotalCompletedCourse(student);

        return HistoryResponse.of(totalCompletedCourse, level);
    }
}
