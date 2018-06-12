package me.yqiang.movie.domain;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Audience {
    @Value("${audience.clientId}")
    private String clientId;
    @Value("${audience.base64Secret}")
    private String base64Secret;
    @Value("${audience.name}")
    private String name;
    @Value("${audience.expiresSecond}")
    private int expiresSecond;
}
