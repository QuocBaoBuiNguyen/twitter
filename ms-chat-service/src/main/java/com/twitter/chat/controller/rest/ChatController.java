package com.twitter.chat.controller.rest;

import com.twitter.chat.service.ChatService;
import dto.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gmail.merikbest2015.constants.PathConstants.CHAT_USER_ID;
import static com.gmail.merikbest2015.constants.PathConstants.UI_V1_CHAT;

@RestController
@RequiredArgsConstructor
@RequestMapping(UI_V1_CHAT)
public class ChatController {
    private final ChatService chatService;
    @GetMapping(CHAT_USER_ID)
    public ResponseEntity<ChatResponse> createChat(
            @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(chatService.createChat(userId));
    }
}