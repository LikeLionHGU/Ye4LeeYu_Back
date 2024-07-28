package com.example.ye4leeyu_back.application.dto;

import com.example.ye4leeyu_back.domain.entity.PossibleDisabilityType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PossibleDisabilityTypeDto {
    private Long id;
    private String disabilityType;
    private Long courseId;

    public static PossibleDisabilityTypeDto of(PossibleDisabilityType possibleDisabilityType) {
        return PossibleDisabilityTypeDto.builder()
                .id(possibleDisabilityType.getId())
                .disabilityType(possibleDisabilityType.getDisabilityType())
                .courseId(possibleDisabilityType.getCourse().getId())
                .build();
    }
}
