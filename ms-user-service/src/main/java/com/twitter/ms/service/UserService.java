package com.twitter.ms.service;

import com.gmail.merikbest2015.dto.response.user.UserPrincipalResponse;
import com.gmail.merikbest2015.dto.response.user.UserResponse;
import com.gmail.merikbest2015.exception.ApiRequestException;
import com.gmail.merikbest2015.mapper.BasicMapper;
import com.gmail.merikbest2015.util.AuthUtil;
import com.twitter.ms.dto.response.UserProfileResponse;
import com.twitter.ms.exception.DataNotFoundException;
import com.twitter.ms.mapper.UserMapper;
import com.twitter.ms.repository.BlockUserRepository;
import com.twitter.ms.repository.UserRepository;
import com.twitter.ms.repository.projection.UserPrincipalView;
import com.twitter.ms.repository.projection.UserProfileView;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

import static com.gmail.merikbest2015.constants.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BasicMapper basicMapper;
    private final AuthService authService;
    private final UserRepository userRespository;
    private final BlockUserRepository blockUserRepository;

    public UserProfileResponse getUserProfileById(Long userId) {
        UserProfileView userProfileView = userRespository.getUserProfileById(userId)
                .orElseThrow(() -> new ApiRequestException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        return basicMapper.convertToResponse(userProfileView, UserProfileResponse.class);
    }

    public UserPrincipalResponse getUserByEmail(String email) {
        UserPrincipalView userPrincipalView = userRespository.getUserByEmail(email, UserPrincipalView.class)
                .orElseThrow(() -> new ApiRequestException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        return basicMapper.convertToResponse(userPrincipalView, UserPrincipalResponse.class);
    }

    // Validate
    public boolean isUserExists(Long userId) {
        return userRespository.isUserExists(userId);
    }

    public boolean isUserHavePrivateProfile(Long userId, HttpServletRequest request) {
        Long authUserId= AuthUtil.getAuthenticatedUserId(request);
        return !userRespository.isUserHavePrivateProfile(userId, authUserId);
    }

    public boolean isMyProfileBlockedByUser(Long userId, HttpServletRequest request) {
        Long authUserId= AuthUtil.getAuthenticatedUserId(request);
        return blockUserRepository.isUserBlocked(userId, authUserId);
    }

    public Long getUserPinnedTweetId(Long userId, HttpServletRequest request) {
        return userRespository.getPinnedTweetId(userId);
    }
}
