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

    private List<String> name;

    private List<Integer> calorie;

    public FlaskResponseDto(List<String> name, List<Integer> calorie){
        this.name = name;
        this.calorie = calorie;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(String i : this.name){
            result.append(", ").append(i);
        }
        return result.toString();
    }

}
