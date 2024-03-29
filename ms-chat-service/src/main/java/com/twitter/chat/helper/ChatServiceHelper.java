package com.twitter.chat.helper;

import com.gmail.merikbest2015.dto.response.chat.ChatUserParticipantResponse;
import com.twitter.chat.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatServiceHelper {
    private final UserClient userClient;
    public ChatUserParticipantResponse getChatParticipant(Long userId) {
        return userClient.getChatParticipant(userId);
    }
}
