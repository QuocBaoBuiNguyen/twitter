package com.twitter.ms.controller.rest;

import com.twitter.ms.constants.ApiConstants;
import com.twitter.ms.dto.response.AuthUserResponse;
import com.twitter.ms.dto.response.UserProfileResponse;
import com.twitter.ms.repository.projection.UserProfileView;
import com.twitter.ms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiConstants.APPLICATION_PREFIX)
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfileResponse> getUserByUserId (
            @RequestHeader @Valid HttpHeaders headers,
            @PathVariable(name = "userId") Long userId
        ) {
        UserProfileResponse userProfileResponse = userService.getUserProfileById(userId);
        return new ResponseEntity<>(userProfileResponse, HttpStatus.OK);
    }

}
