package com.knu.dibly.service;

import com.knu.dibly.domain.board.Board;
import com.knu.dibly.domain.board.dto.BoardUpdateRequestDto;
import com.knu.dibly.repository.BoardRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @AfterEach
    void tearDown() {
        boardRepository.deleteAll();
    }

    @Test
    void create() {
    }

    @Test
    void readById() {
    }

    @Test
    void readAll() {
    }

    @Test
    void update() {
        // given

        // 원래 게시글
        Board board = Board.builder()
                .title("title")
                .content("content")
                .build();
        boardRepository.save(board);

        // 수정할 내용
        Board entity = Board.builder()
                .title("after")
                .content("after")
                .build();
        BoardUpdateRequestDto dto = new BoardUpdateRequestDto(entity);


        // when
        Board originBoard = boardRepository.getById(board.getId());
        originBoard.update(dto.getTitle(), dto.getContent());
        boardRepository.save(originBoard);

        // then
        Board afterBoard = boardRepository.getById(board.getId());
        assertThat(afterBoard.getId()).isEqualTo(originBoard.getId());
        assertThat(afterBoard.getTitle()).isEqualTo(entity.getTitle());
    }

    @Test
    void deleteById() {
    }
}