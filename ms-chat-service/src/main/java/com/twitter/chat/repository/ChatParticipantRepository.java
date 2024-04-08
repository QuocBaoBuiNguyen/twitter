package com.twitter.chat.repository;

import com.twitter.chat.model.ChatParticipant;
import com.twitter.chat.repository.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Long> {
    @Query("SELECT chatParticipant.userId FROM ChatParticipant chatParticipant " +
            "WHERE chatParticipant.chat.id = :chatId and chatParticipant.id = :participantId")
    Optional<Long> getParticipantUserId(Long participantId, Long chatId);
}
