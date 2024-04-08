package com.twitter.chat.controller.rest;

import com.gmail.merikbest2015.dto.response.user.UserResponse;
import com.twitter.chat.service.ChatParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gmail.merikbest2015.constants.PathConstants.PARTICIPANT_CHAT_ID;
import static com.gmail.merikbest2015.constants.PathConstants.UI_V1_CHAT;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = UI_V1_CHAT)
public class ChatParticipantController {
    private final ChatParticipantService chatParticipantService;
    @GetMapping(PARTICIPANT_CHAT_ID)
    public ResponseEntity<UserResponse> getParticipant(@PathVariable("participantId") Long participantId,
                                           @PathVariable("chatId") Long chatId) {
        UserResponse userResponse = chatParticipantService.getParticipant(participantId, chatId);
        return ResponseEntity.ok(userResponse);
    }
}
