package com.example.ye4leeyu_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ye4LeeYuBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ye4LeeYuBackApplication.class, args);
    }

}
