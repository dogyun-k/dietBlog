package com.knu.dibly.domain.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardDeleteRequestDto {

    private Long id;

    public BoardDeleteRequestDto(Long id) {
        this.id = id;
    }

}
