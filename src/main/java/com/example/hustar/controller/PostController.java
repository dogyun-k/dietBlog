package com.example.hustar.controller;

import com.example.hustar.domain.Post;
import com.example.hustar.domain.UploadFile;
import com.example.hustar.service.UploadFileService;
import com.example.hustar.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/posts")
@Controller
public class PostController {

    private final UploadFileService fileService;
    private final PostService postService;

    @GetMapping
    public String readAllPost(Model model) {
        List<Post> posts = postService.readAllPost();
        model.addAttribute("posts", posts);

        return "postsView";
    }

    @GetMapping(value = "/postinfo")
    public String readPost(@RequestParam Long id, Model model) {
        Post post = postService.readPostById(id);
        System.out.println(post.getUploadFile().getStoredPath());
        model.addAttribute("post", post);

        return "postView";
    }

    @GetMapping(value = "/post")
    public String createPostView() {
        return "createPostView";
    }

    @PostMapping(value = "/post")
    public String createPost(@RequestParam String title, @RequestParam String content, @RequestParam MultipartFile imgFile) throws IOException {
        UploadFile uploadFile = fileService.storeFile(imgFile);
        Post post = new Post(title, content, uploadFile);
        postService.createPost(post);

        return "redirect:/posts";
    }

    @GetMapping(value = "/deletepost")
    public String deletePost(@RequestParam Long id) {
        postService.deletePostById(id);
        return "redirect:/posts";
    }

}



















