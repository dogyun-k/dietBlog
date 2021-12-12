package com.knu.dibly.domain.board.dto;

import com.knu.dibly.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardUpdateRequestDto {

    private String title;
    private String content;

    public BoardUpdateRequestDto(Board entity){
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

}
