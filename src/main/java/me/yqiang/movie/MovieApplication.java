package me.yqiang.movie;

import me.yqiang.movie.fliter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieApplication {


    //过滤器
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/jwt/*");
        return registrationBean;
    }
    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}
