package com.example.ye4leeyu_back.config;


import com.example.ye4leeyu_back.application.service.StudentService;
import com.example.ye4leeyu_back.domain.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final StudentService studentService;

    public UserDetails loadStudentByKakaoId(String kakaoId) throws UsernameNotFoundException {
        Student student = studentService.getStudentByKaKaoId(kakaoId);
        return new CustomUserDetails(student.getKakaoId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}