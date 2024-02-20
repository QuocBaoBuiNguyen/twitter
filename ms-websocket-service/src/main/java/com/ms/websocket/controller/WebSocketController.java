package com.ms.websocket.controller;

import com.gmail.merikbest2015.configuration.ApplicationConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static com.gmail.merikbest2015.constants.PathConstants.API_V1;
import static com.gmail.merikbest2015.constants.PathConstants.API_V1_WEBSOCKET;


@RestController
@RequestMapping(value = API_V1_WEBSOCKET)
@RequiredArgsConstructor
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public void send(@RequestParam("destination") String destination, @RequestBody Object request) {
        messagingTemplate.convertAndSend(destination, request);
    }
}
