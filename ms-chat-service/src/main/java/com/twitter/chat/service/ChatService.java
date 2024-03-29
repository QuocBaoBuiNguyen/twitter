package com.twitter.chat.service;

import com.gmail.merikbest2015.mapper.BasicMapper;
import com.gmail.merikbest2015.util.AuthUtil;
import com.twitter.chat.model.Chat;
import com.twitter.chat.model.ChatParticipant;
import com.twitter.chat.repository.ChatParticipantRepository;
import com.twitter.chat.repository.ChatRepository;
import com.twitter.chat.repository.projection.ChatProjection;
import com.twitter.chat.dto.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final BasicMapper basicMapper;

    @Transactional
    public ChatResponse createChat(Long userId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();

        Optional<Chat> chatOptional = chatRepository.getChatByParticipants(authUserId, userId);

        if (chatOptional.isEmpty()) {
            Chat chat = new Chat();
            ChatParticipant chatParticipant1 = new ChatParticipant(authUserId, chat);
            ChatParticipant chatParticipant2 = new ChatParticipant(userId, chat);

            chatRepository.save(chat);
            chatParticipantRepository.save(chatParticipant1);
            chatParticipantRepository.save(chatParticipant2);

            chat.setParticipants(List.of(chatParticipant1, chatParticipant2));

            ChatProjection chatProjection = chatRepository.getChatById(chat.getId());
            return basicMapper.convertToResponse(chatProjection, ChatResponse.class);
        }
        return basicMapper.convertToResponse(
                chatRepository.getChatById(chatOptional.get().getId()),
                ChatResponse.class);
    }

}
