package com.twitter.ms.controller.rest;

import com.twitter.ms.dto.response.AuthResponse;
import com.twitter.ms.dto.response.UserProfileResponse;
import com.twitter.ms.service.AuthService;
import com.twitter.ms.service.UserService;
import com.twitter.ms.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.gmail.merikbest2015.constants.PathConstants.AUTH_USER_ID_HEADER;
import static com.gmail.merikbest2015.constants.PathConstants.UI_V1;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = UI_V1)
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfileResponse> getUserByUserId (
            @RequestHeader @Valid HttpHeaders headers,
            @PathVariable(name = "userId") Long userId
        ) {
        UserProfileResponse userProfileResponse = userService.getUserProfileById(userId);
        return new ResponseEntity<>(userProfileResponse, HttpStatus.OK);
    }

//    @GetMapping("/token")
//    public ResponseEntity<AuthResponse> getUserByToken(
//            HttpServletRequest request
//    ) {
//        String userId = Utils.parseUserId(request);
//        AuthResponse authResponse = authService.getUserByToken(userId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
