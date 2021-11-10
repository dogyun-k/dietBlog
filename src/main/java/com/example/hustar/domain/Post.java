package com.example.hustar.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID")
    private Long id;

    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private String calorieInfo;

    @Temporal(TemporalType.DATE)
    private Date createDate = new Date();

    @OneToOne
    @JoinColumn(name = "FILE_ID")
    private UploadFile uploadFile;

    protected Post() {
    }

    public Post(String title, String content, String calorieInfo, UploadFile uploadFile) {
        this.title = title;
        this.content = content;
        this.calorieInfo = calorieInfo;
        this.uploadFile = uploadFile;
    }

}
