package com.knu.dibly.service;

import com.knu.dibly.domain.board.Board;
import com.knu.dibly.domain.board.dto.BoardCreateRequestDto;
import com.knu.dibly.domain.board.dto.BoardUpdateRequestDto;
import com.knu.dibly.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void create(BoardCreateRequestDto entity) {
        Board board = Board.builder()
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
        boardRepository.save(board);
    }

    public Board readById(Long id) {
        return boardRepository.getById(id);
    }

    public List<Board> readAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public void update(Long id, BoardUpdateRequestDto dto) {
        Board board = boardRepository.getById(id);
        board.update(dto.getTitle(), dto.getContent());
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

}
