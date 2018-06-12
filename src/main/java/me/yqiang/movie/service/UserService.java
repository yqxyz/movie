package me.yqiang.movie.service;

import me.yqiang.movie.domain.User;

public interface UserService {
    User findByUserName(String userName);

    User save(User user);
}
