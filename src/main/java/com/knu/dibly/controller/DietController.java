package com.knu.dibly.controller;

import com.knu.dibly.api.FlaskApi;
import com.knu.dibly.api.dto.FlaskResponseDto;
import com.knu.dibly.config.auth.dto.SessionUser;
import com.knu.dibly.domain.diet.Diet;
import com.knu.dibly.domain.diet.dto.DietDeleteRequestDto;
import com.knu.dibly.domain.diet.dto.DietUpdateRequestDto;
import com.knu.dibly.domain.diet.UploadFile;
import com.knu.dibly.domain.user.User;
import com.knu.dibly.service.DietService;
import com.knu.dibly.service.UploadFileService;
import com.knu.dibly.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping(value = "/diet")
@Controller
public class DietController {

    private final UserService userService;
    private final UploadFileService fileService;
    private final DietService dietService;
    private final FlaskApi flaskApi;
    private final HttpSession httpSession;

    @GetMapping
    public String viewAllDiet(Model model) {

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Optional<User> user = userService.findByEmail(sessionUser.getEmail());

        if (user.isEmpty()) {
            return "redirect:/login";
        }

        model.addAttribute("userName", user.get().getName());
        model.addAttribute("diets", dietService.findAllByUser(user.get()));

        return "diet/allView";
    }

    @GetMapping(value = "/{id}")
    public String viewDiet(@PathVariable Long id, Model model) {
        Diet diet = dietService.readPostById(id);
        model.addAttribute("diet", diet);

        return "diet/oneView";
    }

    @GetMapping(value = "/new")
    public String viewCreateDiet() {
        return "diet/createView";
    }

    @PostMapping
    public String createDiet(@RequestParam String mealType, @RequestParam String content, @RequestParam MultipartFile imgFile) throws Exception {

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Optional<User> user = Optional.empty();

        if (sessionUser != null) {
            user = userService.findByEmail(sessionUser.getEmail());
        }
        else{
            return "redirect:/login";
        }

        String storeFileName = fileService.createStoreFileName(imgFile.getOriginalFilename());
        FlaskResponseDto calorieInfo = flaskApi.requestToFlask(storeFileName, imgFile);
        UploadFile uploadFile = fileService.storeFile(imgFile);

        // 전체 칼로리 계산
        Integer totalCalorie = 0;
        for (Integer i : calorieInfo.getCalorie()) {
            totalCalorie += i;
        }

        if (user.isPresent()) {
            Diet diet = new Diet(mealType, content, calorieInfo.getFoodname().toString(), totalCalorie, uploadFile, user.get());
            dietService.createDiet(diet);
        }

        return "redirect:/diet";
    }

    @GetMapping(value = "/update/{id}")
    public String viewUpdateDiet(Model model, @PathVariable String id) {
        model.addAttribute("diet", dietService.readPostById(Long.valueOf(id)));

        return "diet/updateView";
    }

    @PostMapping(value = "/update/{id}")
    public String updateDiet(@PathVariable String id, @RequestBody DietUpdateRequestDto requestDto) {
        dietService.updateDiet(Long.valueOf(id), requestDto);
        return "redirect:/diet";
    }

    @ResponseBody
    @PostMapping(value = "/delete")
    public String deleteDiet(@RequestBody DietDeleteRequestDto dto) {
        dietService.deleteDietById(dto.getId());
        return "redirect:/diet";
    }

}



















