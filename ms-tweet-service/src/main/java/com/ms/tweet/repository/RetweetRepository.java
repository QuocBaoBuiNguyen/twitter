package com.ms.tweet.repository;

import com.ms.tweet.model.Retweet;
import com.ms.tweet.repository.projection.RetweetProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    @Query("SELECT retweet FROM Retweet retweet " +
            "WHERE retweet.userId = :userId " +
            "ORDER BY retweet.retweetDate DESC")
    List<RetweetProjection> getRetweetsByUserId(@Param("userId") Long userId);

}
