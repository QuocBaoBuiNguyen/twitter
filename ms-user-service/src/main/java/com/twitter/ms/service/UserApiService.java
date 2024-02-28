package com.twitter.ms.service;

import com.gmail.merikbest2015.dto.response.tweet.TweetAuthorResponse;
import com.gmail.merikbest2015.mapper.BasicMapper;
import com.gmail.merikbest2015.util.AuthUtil;
import com.twitter.ms.repository.BlockUserRepository;
import com.twitter.ms.repository.FollowerUserRepository;
import com.twitter.ms.repository.UserRepository;
import com.twitter.ms.repository.projection.TweetAuthorProjection;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserApiService {
    private final BasicMapper basicMapper;
    private final UserRepository userRepository;
    private final BlockUserRepository blockUserRepository;
    private final FollowerUserRepository followerUserRepository;
    // Validate
    public boolean isUserExists(Long userId) {
        return userRepository.isUserExists(userId);
    }

    public boolean isUserHavePrivateProfile(Long userId, HttpServletRequest request) {
        Long authUserId= AuthUtil.getAuthenticatedUserId(request);
        return !userRepository.isUserHavePrivateProfile(userId, authUserId);
    }

    public boolean isMyProfileBlockedByUser(Long userId, HttpServletRequest request) {
        Long authUserId= AuthUtil.getAuthenticatedUserId(request);
        return blockUserRepository.isUserBlocked(userId, authUserId);
    }

    public boolean isUserFollowByOtherUser(Long userId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return followerUserRepository.isUserFollowByOtherUser(authUserId, userId);
    }

    public Long getUserPinnedTweetId(Long userId, HttpServletRequest request) {
        return userRepository.getPinnedTweetId(userId);
    }

    @Transactional
    public void updateMediaTweetCount(boolean increase) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        userRepository.updateMediaTweetCount(increase, authUserId);
    }

    @Transactional
    public void updateTweetCount(boolean increase) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        userRepository.updateTweetCount(increase, authUserId);
    }

    public TweetAuthorResponse getTweetAuthor(Long userId) {
        TweetAuthorProjection authorProjection = userRepository.getUserById(userId, TweetAuthorProjection.class)
                .get();
        return basicMapper.convertToResponse(authorProjection, TweetAuthorResponse.class);
    }
}
