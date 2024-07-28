package com.example.ye4leeyu_back.presentation.response;

import com.example.ye4leeyu_back.application.dto.PossibleDisabilityTypeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PossibleDisabilityTypeResponse {
    private String disabilityType;

    public static PossibleDisabilityTypeResponse of(PossibleDisabilityTypeDto possibleDisabilityType) {
        return PossibleDisabilityTypeResponse.builder()
                .disabilityType(possibleDisabilityType.getDisabilityType())
                .build();
    }
}
