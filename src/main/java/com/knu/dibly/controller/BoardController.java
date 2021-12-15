package com.knu.dibly.controller;

import com.knu.dibly.config.auth.dto.SessionUser;
import com.knu.dibly.domain.board.dto.BoardCreateRequestDto;
import com.knu.dibly.domain.board.dto.BoardDeleteRequestDto;
import com.knu.dibly.domain.board.dto.BoardUpdateRequestDto;
import com.knu.dibly.domain.user.User;
import com.knu.dibly.service.BoardService;
import com.knu.dibly.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping(value = "/board")
@Controller
public class BoardController {

    private final HttpSession httpSession;
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping
    public String board(Model model) {
        model.addAttribute("boardList", boardService.readAll());
        return "board/allView";
    }

    @GetMapping(value = "/new")
    public String createForm() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if(sessionUser.getEmail().isEmpty()){
            return "redirect:/login";
        }

        return "board/createView";
    }

    @PostMapping(value = "/new")
    public String create(@RequestBody BoardCreateRequestDto boardCreateRequestDto) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        User user = userService.findByEmail(sessionUser.getEmail()).get();

        boardService.create(user, boardCreateRequestDto);
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

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto dto){
        boardService.update(id, dto);
        return "redirect:/board";
    }

    @ResponseBody
    @PostMapping(value = "/delete")
    public void delete(@RequestBody BoardDeleteRequestDto dto) {
        boardService.deleteById(dto.getId());
    }
}
