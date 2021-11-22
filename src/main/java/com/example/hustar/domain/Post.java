package com.example.hustar.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    private String mealType;

    private String foods;

    private Integer calorieInfo;

    @Temporal(TemporalType.DATE)
    private Date createDate = new Date();

    @OneToOne
    @JoinColumn(name = "FILE_ID")
    private UploadFile uploadFile;

    @ManyToOne
    @JoinColumn(name = "USER_ID")       // User_ID 키를 관리할 주인은 POST와 USER중 누가 해야할까?
    private User user;

    protected Post() {
    }

    public Post(String content, String foods, Integer calorieInfo, UploadFile uploadFile) {
        this.content = content;
        this.foods = foods;
        this.calorieInfo = calorieInfo;
        this.uploadFile = uploadFile;
    }

}
