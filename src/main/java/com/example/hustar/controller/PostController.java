package com.example.hustar.controller;

import com.example.hustar.api.FlaskApi;
import com.example.hustar.domain.Post;
import com.example.hustar.domain.UploadFile;
import com.example.hustar.service.PostService;
import com.example.hustar.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/posts")
@Controller
public class PostController {

    private final UploadFileService fileService;
    private final PostService postService;
    private final FlaskApi flaskApi;

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
    public String createPost(@RequestParam String title, @RequestParam String content, @RequestParam MultipartFile imgFile) throws Exception {
        UploadFile uploadFile = fileService.storeFile(imgFile);
        Post post = new Post(title, content, uploadFile);

        String calorie = flaskApi.requestToFlask(uploadFile.getUploadFileName(), imgFile);
        System.out.println(calorie);

        postService.createPost(post);

        return "redirect:/posts";
    }

    @GetMapping(value = "/deletepost")
    public String deletePost(@RequestParam Long id) {
        postService.deletePostById(id);
        return "redirect:/posts";
    }

}



















