package com.example.ye4leeyu_back.presentation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginTokenResponse {
    String token;

    public static LoginTokenResponse of(String token) {
        return LoginTokenResponse.builder()
                .token(token)
                .build();
    }
}