package com.example.hustar.controller;

import com.example.hustar.api.FlaskApi;
import com.example.hustar.config.auth.dto.SessionUser;
import com.example.hustar.domain.FlaskResponseDto;
import com.example.hustar.domain.Post;
import com.example.hustar.domain.UploadFile;
import com.example.hustar.service.PostService;
import com.example.hustar.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;

@RequiredArgsConstructor
@RequestMapping(value = "/posts")
@Controller
public class PostController {

    private final UploadFileService fileService;
    private final PostService postService;
    private final FlaskApi flaskApi;
    private final HttpSession httpSession;

    @GetMapping
    public String viewAllPost(Model model) {
        model.addAttribute("posts", postService.readAllPost());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "postsView";
    }

    @GetMapping(value = "/postinfo")
    public String viewPost(@RequestParam Long id, Model model) {
        Post post = postService.readPostById(id);
        model.addAttribute("post", post);

        return "postView";
    }

    @ResponseBody
    @GetMapping(value = "/image/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getFullPath(filename));
    }

    @GetMapping(value = "/post")
    public String viewCreatePost() {
        return "createPostView";
    }

    @PostMapping(value = "/post")
    public String createPost(@RequestParam String content, @RequestParam MultipartFile imgFile) throws Exception {

        FlaskResponseDto calorieInfo = flaskApi.requestToFlask(fileService.createStoreFileName(imgFile.getOriginalFilename()), imgFile);
        UploadFile uploadFile = fileService.storeFile(imgFile);

        // 전체 칼로리 계산
        Integer totalCalorie = 0;
        for (Integer i : calorieInfo.getCalorie()) {
            totalCalorie += i;
        }

        Post post = new Post(content, calorieInfo.getFoodname().toString(), totalCalorie, uploadFile);
        postService.createPost(post);

        return "redirect:/posts";
    }

    @GetMapping(value = "/deletepost")
    public String deletePost(@RequestParam Long id) {
        postService.deletePostById(id);
        return "redirect:/posts";
    }

}



















