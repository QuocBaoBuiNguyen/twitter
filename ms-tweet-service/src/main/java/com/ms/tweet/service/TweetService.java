package com.ms.tweet.service;

import com.gmail.merikbest2015.dto.HeaderResponse;
import com.gmail.merikbest2015.dto.response.tweet.TweetResponse;
import com.gmail.merikbest2015.mapper.BasicMapper;
import com.gmail.merikbest2015.util.AuthUtil;
import com.ms.tweet.client.UserClient;
import com.ms.tweet.dto.request.TweetRequest;
import com.ms.tweet.dto.response.ProfileTweetImageResponse;
import com.ms.tweet.dto.response.TweetUserResponse;
import com.ms.tweet.helper.TweetServiceHelper;
import com.ms.tweet.helper.TweetValidationHelper;
import com.ms.tweet.model.Tweet;
import com.ms.tweet.repository.RetweetRepository;
import com.ms.tweet.repository.TweetRepository;
import com.ms.tweet.repository.projection.ProfileTweetImageProjection;
import com.ms.tweet.repository.projection.RetweetProjection;
import com.ms.tweet.repository.projection.TweetUserProjection;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final UserClient userClient;
    private final TweetRepository tweetRepository;
    private final RetweetRepository retweetRepository;
    private final TweetServiceHelper tweetServiceHelper;
    private final TweetValidationHelper tweetValidationHelper;
    private final BasicMapper basicMapper;
    @Transactional(readOnly = true)
    public HeaderResponse<TweetUserResponse> getUserTweets(Long userId, Pageable pageable) {
        tweetValidationHelper.validateUserProfile(userId);
        // TODO: check N + 1 query here for images field
        List<TweetUserProjection> tweets = tweetRepository.getTweetsByUserId(userId);
        List<RetweetProjection> retweets = retweetRepository.getRetweetsByUserId(userId);
        List<TweetUserProjection> userTweets = tweetServiceHelper.combineTweetsArraysOnDateOrderDsc(tweets, retweets);
        Long pinnedTweetId = userClient.getUserPinnedTweetId(userId);

        if (pinnedTweetId != null) {
            TweetUserProjection pinnedTweet = tweetRepository.getTweetById(pinnedTweetId, TweetUserProjection.class).get();
            boolean isTweetExist = userTweets.removeIf(tweet -> tweet.getId().equals(pinnedTweet.getId()));

            if (isTweetExist) {
                userTweets.add(0, pinnedTweet);
            }
        }
        Page<TweetUserProjection> tweetsPageable = tweetServiceHelper.getPageableTweetProjectionList(pageable, userTweets, tweets.size() + retweets.size());
        return basicMapper.getHeaderResponse(tweetsPageable, TweetUserResponse.class);
    }

    public List<ProfileTweetImageResponse> getUserTweetImages(Long userId) {
        tweetValidationHelper.validateUserProfile(userId);
        List<ProfileTweetImageProjection> profileTweetImageProjections = tweetRepository.getUserTweetImages(userId, PageRequest.of(0, 6));
        return basicMapper.convertToResponseList(profileTweetImageProjections, ProfileTweetImageResponse.class);
    }

    @Transactional
    public TweetResponse createTweet(TweetRequest tweetRequest) {
        Tweet tweet = basicMapper.convertToResponse(tweetRequest, Tweet.class);
        return tweetServiceHelper.createTweet(tweet);
    }
}
