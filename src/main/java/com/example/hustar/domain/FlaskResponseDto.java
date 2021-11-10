package com.example.hustar.domain;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Data
public class FlaskResponseDto {

    private List<String> foodName;

    private List<String> calorie;

    public FlaskResponseDto(){}

    public FlaskResponseDto(List<String> foodName, List<String> calorie){
        this.foodName = foodName;
        this.calorie = calorie;
    }

}
