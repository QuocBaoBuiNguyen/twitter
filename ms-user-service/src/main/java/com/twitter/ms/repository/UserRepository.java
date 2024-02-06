package com.twitter.ms.repository;

import com.twitter.ms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u.activationCode FROM Users u WHERE u.id = :userId")
    String findActivationCodeByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE Users user SET user.activationCode = :activationCode WHERE user.id = :userId")
    void updateActivationCode(@Param("activationCode") String activationCode,@Param("userId") Long userId);
}
