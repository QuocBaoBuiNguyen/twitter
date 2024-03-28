package com.twitter.chat.repository;

import com.twitter.chat.model.Chat;
import com.twitter.chat.repository.projection.ChatProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT chat FROM Chat chat " +
            "WHERE chat.id = :chatId")
    ChatProjection getChatById(@Param("chatId") Long chatId);

    @Query("SELECT chat FROM Chat chat " +
            "LEFT JOIN chat.chatParticipants cp1 " +
            "LEFT JOIN chat.chatParticipants cp2 " +
            " WHERE cp1.userId = :authUserId AND cp2.userId = :userId AND cp1.id = cp2.id")
    public Optional<Chat> getChatByParticipants(@Param("authUserId") Long authUserId, @Param("userId") Long userId);
}
