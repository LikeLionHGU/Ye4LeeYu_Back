package com.example.ye4leeyu_back.application.dto;

import com.example.ye4leeyu_back.domain.entity.Coupon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CouponDto {
    private Long id;
    private String name;
    private int discountRate;
    private Long studentId;

    public static CouponDto of(Coupon coupon) {
        return CouponDto.builder()
                .id(coupon.getId())
                .name(coupon.getName())
                .discountRate(coupon.getDiscountRate())
                .studentId(coupon.getStudent().getId())
                .build();
    }
}
