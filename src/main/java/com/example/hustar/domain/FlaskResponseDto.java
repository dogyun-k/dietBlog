package com.example.hustar.domain;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class FlaskResponseDto {

    private String foodName;

    private Integer calorie;

    public FlaskResponseDto(String foodName, Integer calorie){
        this.foodName = foodName;
        this.calorie = calorie;
    }

}
