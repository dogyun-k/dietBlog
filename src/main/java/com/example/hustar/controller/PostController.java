package com.example.hustar.controller;

import com.example.hustar.api.FlaskApi;
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

import java.net.MalformedURLException;
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

    @ResponseBody
    @GetMapping(value = "/image/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getFullPath(filename));
    }

    @GetMapping(value = "/post")
    public String createPostView() {
        return "createPostView";
    }

    @PostMapping(value = "/post")
    public String createPost(@RequestParam String title, @RequestParam String content, @RequestParam MultipartFile imgFile) throws Exception {

        String storeFileName = fileService.createStoreFileName(imgFile.getOriginalFilename());
        String calorie = flaskApi.requestToFlask(storeFileName, imgFile);
        UploadFile uploadFile = fileService.storeFile(imgFile);

        Post post = new Post(title, content, calorie, uploadFile);
        postService.createPost(post);

        return "redirect:/posts";
    }

    @GetMapping(value = "/deletepost")
    public String deletePost(@RequestParam Long id) {
        postService.deletePostById(id);
        return "redirect:/posts";
    }

}



















