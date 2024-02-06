package com.twitter.ms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class RegistrationResponse {
    private String username;
    private String message;
}
