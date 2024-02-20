package com.twitter.ms.repository;

import com.twitter.ms.model.User;
import com.twitter.ms.repository.projection.UserProfileView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //QUERY
    Optional<User> findByEmail(String email);
    <T> Optional<T> getUserById(Long userId, Class<T> projections);

    <T> Optional<T> getUserByEmail(String email, Class<T> projections);

    Optional<UserProfileView> getUserProfileById(Long id);

    @Query("SELECT u.activationCode FROM User u WHERE u.id = :userId")
    String findActivationCodeByUserId(@Param("userId") Long userId);

    @Query("SELECT " +
            "CASE " +
                "WHEN count(user) > 0 THEN true " +
                "ELSE false " +
            "END " +
            "FROM User user " +
            "WHERE user.id = :userId")
    Boolean isUserExists(@Param("userId") Long userId);

    @Query("SELECT user.privateProfile FROM User user WHERE user.id = :userId")
    Boolean isPrivateProfile(@Param("userId") Long userId);

    @Query("SELECT CASE " +
            "WHEN count(user) > 0 THEN true ELSE false " +
            "END " +
            "FROM User user " +
            "LEFT JOIN user.following following " +
            "WHERE user.id = :userId AND user.privateProfile = false " +
            "OR user.id = :userId AND user.privateProfile = true AND following.id = :authUserId")
    Boolean isUserHavePrivateProfile(@Param("userId") Long userId, @Param("authUserId") Long authUserId);
    @Query("SELECT user.pinnedTweetId FROM User user WHERE user.id = :userId")
    Long getPinnedTweetId(@Param("userId") Long userId);

    //UPDATE
    @Modifying
    @Query("UPDATE User user SET user.activationCode = :activationCode WHERE user.id = :userId")
    void updateActivationCode(@Param("activationCode") String activationCode, @Param("userId") Long userId);

    @Modifying
    @Query("UPDATE User user SET user.active = :active WHERE user.id = :userId")
    void updateAccountStatus(@Param("active") Boolean active, @Param("userId") Long userId);

    @Modifying
    @Query("UPDATE User user SET user.password = :password WHERE user.id = :userId")
    void updatePassword(@Param("password") String password, @Param("userId") Long userId);
}
