package com.twitter.ms.service;

import com.gmail.merikbest2015.exception.ApiRequestException;
import com.gmail.merikbest2015.security.JwtProvider;
import com.twitter.ms.dto.request.AuthRequest;
import com.twitter.ms.dto.response.AuthResponse;
import com.twitter.ms.exception.DataNotFoundException;
import com.twitter.ms.mapper.UserMapper;
import com.twitter.ms.model.User;
import com.twitter.ms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail())
                        .orElseThrow(() -> new DataNotFoundException("User", "User/email is not existed", HttpStatus.NOT_FOUND));
        Boolean isMatched = passwordEncoder.matches(authRequest.getPassword(), user.getPassword());
        if (!isMatched) {
            throw new ApiRequestException("Incorrect password!", HttpStatus.FORBIDDEN);
        }
        String accessToken = jwtProvider.createToken(user.getEmail(), "USER");
        return AuthResponse.builder()
                .authUserResponse(UserMapper.INSTANCE.userEntityToAuthUserDTO(user))
                .accessToken(accessToken)
                .build();
    }
}
