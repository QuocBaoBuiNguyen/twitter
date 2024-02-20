package com.ms.tweet.helper;

import com.ms.tweet.client.UserClient;
import com.ms.tweet.repository.TweetRepository;
import com.ms.tweet.repository.projection.TweetUserProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TweetProjectionHelper {
    private final TweetRepository tweetRepository;
    private final UserClient userClient;

    public TweetUserProjection getTweetUserProjection(Long tweetId) {
        return tweetRepository.getTweetById(tweetId, TweetUserProjection.class).get();
    }

}
