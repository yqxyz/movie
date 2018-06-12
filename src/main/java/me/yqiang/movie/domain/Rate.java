package me.yqiang.movie.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "movie_id",nullable = false,length = 20)
    private Long movieId;
    @Column(name = "user_id",nullable = false,length = 20)
    private Long userId;
    @Column(name = "rate",nullable = false)
    private Double rate;
    @Transient
    private Integer num;
}
