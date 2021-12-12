package com.knu.dibly.domain.diet.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DietDeleteRequestDto {

    private Long id;

    public DietDeleteRequestDto(Long id){
        this.id = id;
    }
}
