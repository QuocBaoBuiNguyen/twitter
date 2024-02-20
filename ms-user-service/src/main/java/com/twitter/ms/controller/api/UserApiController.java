package com.twitter.ms.controller.api;

import com.twitter.ms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import static com.gmail.merikbest2015.constants.PathConstants.*;

@RestController
@RequestMapping(value = API_V1_USER)
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    @GetMapping(IS_EXISTS_USER_ID)
    public Boolean isUserExist(@PathVariable(name = "userId") Long userId) {
        return userService.isUserExists(userId);
    }

    @GetMapping(IS_PRIVATE_USER_ID)
    public Boolean isUserHavePrivateProfile(@PathVariable(name = "userId") Long userId,
                                    HttpServletRequest request) {
        return userService.isUserHavePrivateProfile(userId, request);
    }

    @GetMapping(IS_MY_PROFILE_BLOCKED_USER_ID)
    public Boolean isMyProfileBlockedByUser(@PathVariable("userId") Long userId,
                                            HttpServletRequest request) {
        return userService.isMyProfileBlockedByUser(userId, request);
    }

    @GetMapping(TWEET_PINNED_USER_ID)
    public Long getUserPinnedTweetId(@PathVariable("userId") Long userId, HttpServletRequest request) {
        return userService.getUserPinnedTweetId(userId, request);
    }
}
