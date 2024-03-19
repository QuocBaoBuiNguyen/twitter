package com.twitter.ms.controller.api;

import com.gmail.merikbest2015.dto.request.IdsRequest;
import com.gmail.merikbest2015.dto.response.tweet.TweetAuthorResponse;
import com.twitter.ms.service.UserApiService;
import com.twitter.ms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.merikbest2015.constants.PathConstants.*;

@RestController
@RequestMapping(value = API_V1_USER)
@RequiredArgsConstructor
public class UserApiController {
    private final UserApiService userService;
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

    @GetMapping(IS_FOLLOWED_USER_ID)
    public Boolean isUserFollowByOtherUser(@PathVariable("userId") Long userId) {
        return userService.isUserFollowByOtherUser(userId);
    }

    @PutMapping(MEDIA_COUNT)
    public void updateMediaTweetCount(@PathVariable("increaseCount") boolean increaseCount) {
        userService.updateMediaTweetCount(increaseCount);
    }

    @PutMapping(TWEET_COUNT)
    public void updateTweetCount(@PathVariable("increaseCount") boolean increaseCount) {
        userService.updateTweetCount(increaseCount);
    }

    @GetMapping(TWEET_AUTHOR_USER_ID)
    public TweetAuthorResponse getTweetAuthor(@PathVariable("userId") Long userId) {
        return userService.getTweetAuthor(userId);
    }

    @PostMapping(VALID_IDS)
    public List<Long> getValidUserIds(@RequestBody IdsRequest request) {
        return userService.getValidUserIds(request);
    }
}
