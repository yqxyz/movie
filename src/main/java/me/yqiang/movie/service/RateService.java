package me.yqiang.movie.service;

import me.yqiang.movie.domain.Rate;

import java.util.List;

public interface RateService {
    Rate save(Rate rate);
    List<Rate> findByUserId(Long userId);
    List<?> findAllGroupByMovieId();
}
