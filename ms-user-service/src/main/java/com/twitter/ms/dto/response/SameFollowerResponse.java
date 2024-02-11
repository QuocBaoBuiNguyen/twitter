package com.twitter.ms.dto.response;

import lombok.Data;

@Data
public class SameFollowerResponse {
    private Long id;
    private String fullName;
    private String username;
    private String avatar;
}
