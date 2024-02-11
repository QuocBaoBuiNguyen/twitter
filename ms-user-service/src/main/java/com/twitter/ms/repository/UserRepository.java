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
    Optional<User> findByEmail(String email);

    <T> Optional<T> getUserByEmail(String email, Class<T> projections);

    Optional<UserProfileView> getUserProfileById(Long id);

    @Query("SELECT u.activationCode FROM User u WHERE u.id = :userId")
    String findActivationCodeByUserId(@Param("userId") Long userId);

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
