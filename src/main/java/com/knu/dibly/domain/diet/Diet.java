package com.knu.dibly.domain.diet;

import com.knu.dibly.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@ToString
@Getter
@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id")
    private Long id;

    @Lob
    private String content;

    private String mealType;

    private String foods;

    private Integer calorieInfo;

    @Temporal(TemporalType.DATE)
    private Date createDate = new Date();

    @OneToOne
    @JoinColumn(name = "file_id")
    private UploadFile uploadFile;

    @ManyToOne
    @JoinColumn(name = "user_id")       // User_ID 키를 관리할 주인은 POST와 USER중 누가 해야할까?
    private User user;

    public Diet(String mealType, String content, String foods, Integer calorieInfo, UploadFile uploadFile, User user) {
        this.mealType = mealType;
        this.content = content;
        this.foods = foods;
        this.calorieInfo = calorieInfo;
        this.uploadFile = uploadFile;
        this.user = user;
    }

    public void update(String mealType, String content) {
        this.mealType = mealType;
        this.content = content;
    }

}
