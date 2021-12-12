package com.knu.dibly.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Data
public class FlaskResponseDto {

    private List<String> foodname;

    private List<Integer> calorie;

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
