package com.example.ye4leeyu_back.presentation.response;

import com.example.ye4leeyu_back.application.dto.CouponDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CouponResponse {
    private Long id;
    private String name;
    private int discountRate;

    public static CouponResponse of(CouponDto couponDto) {
        return CouponResponse.builder()
                .id(couponDto.getId())
                .name(couponDto.getName())
                .discountRate(couponDto.getDiscountRate())
                .build();
    }
}
