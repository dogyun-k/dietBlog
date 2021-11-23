package com.example.hustar.service;

import com.example.hustar.domain.post.Post;
import com.example.hustar.domain.user.User;
import com.example.hustar.repository.PostRepository;
import com.example.hustar.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UploadFileRepository fileRepository;
    private final PostRepository postRepository;

    public void createPost(Post post) {
        postRepository.save(post);
    }

    public Post readPostById(Long id) {
        return postRepository.getById(id);
    }

    public List<Post> readAllPost() {
        return postRepository.findAll();
    }

    // TODO Post Update
    public void updatePost(Post post) {

    }

    public void deletePostById(Long id) {
        Long fileId = postRepository.getById(id).getUploadFile().getId();
        postRepository.deleteById(id);
        fileRepository.deleteById(fileId);
    }

    public List<Post> findAllByUser(User user){
        return postRepository.findAllByUser(user);
    }

}
