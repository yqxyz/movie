package me.yqiang.movie.repository;

import me.yqiang.movie.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate,Long> {
    List<Rate> findByUserId(Long userId);
    @Query(value = "SELECT  rate.movie_id AS movieId ,AVG(rate.rate) AS rate,COUNT(rate.movie_id) AS num FROM rate GROUP BY rate.movie_id",nativeQuery = true)
    List<?> findRateCount();
}
