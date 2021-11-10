package com.example.hustar.domain;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Data
public class FlaskResponseDto {

    private List<String> foodname;

    private List<Integer> calorie;

    public FlaskResponseDto(){}

    public FlaskResponseDto(List<String> foodname, List<Integer> calorie){
        this.foodname = foodname;
        this.calorie = calorie;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(String i : this.foodname){
            result.append(", ").append(i);
        }
        return result.toString();
    }

}
