package com.example.hustar.controller;

import com.example.hustar.api.FlaskApi;
import com.example.hustar.config.auth.dto.SessionUser;
import com.example.hustar.domain.post.FlaskResponseDto;
import com.example.hustar.domain.post.Post;
import com.example.hustar.domain.post.UploadFile;
import com.example.hustar.domain.user.User;
import com.example.hustar.service.PostService;
import com.example.hustar.service.UploadFileService;
import com.example.hustar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping(value = "/posts")
@Controller
public class PostController {

    private final UserService userService;
    private final UploadFileService fileService;
    private final PostService postService;
    private final FlaskApi flaskApi;
    private final HttpSession httpSession;

    @GetMapping
    public String viewAllPost(Model model) {

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Optional<User> user = userService.findByEmail(sessionUser.getEmail());
        if (user.isPresent()){
            model.addAttribute("posts", postService.readAllPost());
//            model.addAttribute("posts", postService.findAllByUser(user.get()));
            model.addAttribute("userName", user.get().getName());
        }

        return "post/postsView";
    }

    @GetMapping(value = "/post/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.readPostById(id);
        model.addAttribute("post", post);

        return "post/postView";
    }

    @GetMapping(value = "/post")
    public String viewCreatePost() {
        return "post/createPostView";
    }

    @PostMapping(value = "/post")
    public String createPost(@RequestParam String mealType, @RequestParam String content, @RequestParam MultipartFile imgFile) throws Exception {

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Optional<User> user = Optional.empty();
        if (sessionUser != null) {
            user = userService.findByEmail(sessionUser.getEmail());
        }

        String storeFileName = fileService.createStoreFileName(imgFile.getOriginalFilename());
        FlaskResponseDto calorieInfo = flaskApi.requestToFlask(storeFileName, imgFile);
        UploadFile uploadFile = fileService.storeFile(imgFile);

        // 전체 칼로리 계산
        Integer totalCalorie = 0;
        for (Integer i : calorieInfo.getCalorie()) {
            totalCalorie += i;
        }

        if (user.isPresent()){
            Post post = new Post(mealType, content, calorieInfo.getFoodname().toString(), totalCalorie, uploadFile, user.get());
            postService.createPost(post);
        }

        return "redirect:/posts";
    }

    @DeleteMapping(value = "/post/{id}")
    public Long deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return id;
    }

}



















