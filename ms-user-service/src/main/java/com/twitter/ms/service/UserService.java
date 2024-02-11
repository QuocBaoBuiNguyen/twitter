package com.twitter.ms.service;

import com.gmail.merikbest2015.dto.response.user.UserPrincipalResponse;
import com.gmail.merikbest2015.dto.response.user.UserResponse;
import com.gmail.merikbest2015.exception.ApiRequestException;
import com.gmail.merikbest2015.mapper.BasicMapper;
import com.twitter.ms.dto.response.UserProfileResponse;
import com.twitter.ms.exception.DataNotFoundException;
import com.twitter.ms.mapper.UserMapper;
import com.twitter.ms.repository.UserRepository;
import com.twitter.ms.repository.projection.UserPrincipalView;
import com.twitter.ms.repository.projection.UserProfileView;
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
    private final UserRepository userRespository;

    public UserProfileResponse getUserProfileById(Long userId) {
        UserProfileView userProfileView = userRespository.getUserProfileById(userId)
                .orElseThrow(() -> new ApiRequestException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        UserProfileResponse userProfileResponse = basicMapper.convertToResponse(userProfileView, UserProfileResponse.class);
        return userProfileResponse;
    }

    public UserPrincipalResponse getUserByEmail(String email) {
        UserPrincipalView userPrincipalView = userRespository.getUserByEmail(email, UserPrincipalView.class)
                .orElseThrow(() -> new ApiRequestException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        UserPrincipalResponse userPrincipalResponse = basicMapper.convertToResponse(userPrincipalView, UserPrincipalResponse.class);
        return userPrincipalResponse;
    }
}
