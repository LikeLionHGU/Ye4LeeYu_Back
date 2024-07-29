package com.example.ye4leeyu_back.presentation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LikeCountResponse {
    private int likeCount;

    public static LikeCountResponse of(int likeCount) {
        return LikeCountResponse.builder()
                .likeCount(likeCount)
                .build();
    }
}
