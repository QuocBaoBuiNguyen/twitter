package com.twitter.ms.repository;

import com.twitter.ms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT CASE WHEN count(user) > 0 THEN true ELSE false END " +
            "FROM User user " +
            "LEFT JOIN user.followers follower " +
            "WHERE user.id = :authUserId AND follower.id = :userId")
    boolean isUserFollowByOtherUser(@Param("authUserId") Long authUserId, @Param("userId") Long userId);

}
