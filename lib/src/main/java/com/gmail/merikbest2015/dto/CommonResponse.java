package com.gmail.merikbest2015.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CommonResponse {
    private String httpStatus;
    private String message;
}
