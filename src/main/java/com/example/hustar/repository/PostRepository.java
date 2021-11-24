package com.example.hustar.repository;

import com.example.hustar.domain.post.Post;
import com.example.hustar.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);

}
