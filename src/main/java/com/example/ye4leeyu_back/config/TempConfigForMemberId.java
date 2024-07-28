package com.example.ye4leeyu_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TempConfigForMemberId {

    @Bean
    public Long memberId() {
        return 1L;
    }

}
