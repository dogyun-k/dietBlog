package com.example.hustar.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private String calorieInfo;

    @OneToOne
    @JoinColumn(name = "file_id")
    private UploadFile uploadFile;

    public Post() {
    }

    public Post(String title, String content, String calorieInfo, UploadFile uploadFile) {
        this.title = title;
        this.content = content;
        this.calorieInfo = calorieInfo;
        this.uploadFile = uploadFile;
    }

}
