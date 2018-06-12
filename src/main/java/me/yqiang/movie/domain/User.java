package me.yqiang.movie.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name",nullable = false,length = 10)
    private String userName;
    @Column(name = "password",nullable = false,length = 20)
    private String password;
}
