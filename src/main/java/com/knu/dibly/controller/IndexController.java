package com.knu.dibly.controller;

import com.knu.dibly.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/main";
    }

    @GetMapping(value = "/main")
    public String main(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("user", user);
        }

        return "main";
    }
}
