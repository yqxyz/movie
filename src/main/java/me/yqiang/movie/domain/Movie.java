package me.yqiang.movie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "movie_name",nullable = false,length = 20)
    private String movieName;
    @Column(name = "ima_name",nullable = false,length = 20)
    private String imgName;
}
