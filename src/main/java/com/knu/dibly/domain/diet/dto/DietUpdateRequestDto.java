package com.knu.dibly.domain.diet.dto;

import com.knu.dibly.domain.diet.Diet;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DietUpdateRequestDto {

    // 수정해야 할 속성값만 포함한 객체이다.

    private String mealType;
    private String content;

    public DietUpdateRequestDto(Diet entity) {
        this.mealType = entity.getMealType();
        this.content = entity.getContent();
    }
}
