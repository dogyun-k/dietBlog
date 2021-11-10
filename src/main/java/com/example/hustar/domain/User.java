package com.example.hustar.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Lob
    private String description;

    @OneToMany(mappedBy = "user")
    private List<Post> post = new ArrayList<Post>();

    public enum RoleType {
        ADMIN, USER
    }

    // 기본 생성자 ( 다른 생성자가 없으면 굳이 안 해도 됨 )
    public User() {
    }

}
