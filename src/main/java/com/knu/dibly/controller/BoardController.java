package com.knu.dibly.controller;

import com.knu.dibly.domain.board.dto.BoardCreateRequestDto;
import com.knu.dibly.domain.board.dto.BoardDeleteRequestDto;
import com.knu.dibly.domain.board.dto.BoardUpdateRequestDto;
import com.knu.dibly.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = "/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String board(Model model) {
        model.addAttribute("boardList", boardService.readAll());
        return "board/allView";
    }

    @GetMapping(value = "/new")
    public String createForm() {
        return "board/createView";
    }

    @PostMapping(value = "/new")
    public String create(@RequestBody BoardCreateRequestDto boardCreateRequestDto) {
        boardService.create(boardCreateRequestDto);
        return "redirect:/board";
    }

    @GetMapping(value = "/{id}")
    public String read(Model model, @PathVariable Long id) {
        model.addAttribute("board", boardService.readById(id));
        return "board/oneView";
    }

    @GetMapping(value = "/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.readById(id));
        return "board/updateView";
    }

    // TODO 글수정하기 고치기
    @PostMapping(value = "/update/{id}")
    public void update(@PathVariable Long id, BoardUpdateRequestDto dto){
        boardService.update(id, dto);
    }

    @ResponseBody
    @PostMapping(value = "/delete")
    public void delete(@RequestBody BoardDeleteRequestDto dto) {
        boardService.deleteById(dto.getId());
    }
}
