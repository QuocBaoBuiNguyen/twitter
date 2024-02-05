package com.twitter.ms.service;

import com.gmail.merikbest2015.dto.CommonResponse;
import com.gmail.merikbest2015.dto.request.EmailRequest;
import com.twitter.ms.config.AmqpProducer;
import com.twitter.ms.dto.request.RegistrationRequest;
import com.twitter.ms.dto.response.RegistrationResponse;
import com.twitter.ms.exception.RegistrationException;
import com.twitter.ms.mapper.UserMapper;
import com.twitter.ms.model.User;
import com.twitter.ms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final AmqpProducer amqpProducer;

    @Transactional
    public RegistrationResponse registrationValidateService(RegistrationRequest request) {
        String email = request.getEmail();
        Optional<User> existingUser  = userRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            User user = UserMapper.INSTANCE.registrationRequestToUseDAO(request);
            userRepository.saveAndFlush(user);
            return RegistrationResponse.builder()
                    .username(email)
                    .message("Registered successfully")
                    .build();
        }

        if (!existingUser.get().isActive()) {
            existingUser.get().setUsername(request.getUsername());
            existingUser.get().setFullName(request.getUsername());
            existingUser.get().setBirthday(request.getBirthday());
            userRepository.saveAndFlush(existingUser.get());
            return RegistrationResponse.builder()
                    .username(email)
                    .message("Re-activate account")
                    .build();
        }
        throw new RegistrationException("Email", "Email is already registered", HttpStatus.FORBIDDEN);
    }

    @Transactional
    public CommonResponse sendRegistrationCode(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RegistrationException("Email", "Email is already registered", HttpStatus.FORBIDDEN));
        userRepository.updateActivationCode(UUID.randomUUID().toString().substring(0, 7), user.getId());
        String activationCode = userRepository.findActivationCodeByUserId(user.getId());
        EmailRequest emailRequest = EmailRequest.builder()
                .to(user.getEmail())
                .subject("Validation code for Twitter registration")
                .template("Template")
                .attributes(Map.of(
                        "fullName", user.getFullName(),
                        "registrationCode", activationCode
                ))
                .build();
        amqpProducer.sendEmail(emailRequest);
        return CommonResponse.builder()
                .httpStatus("200 OK")
                .message("Send activation code successfully")
                .build();
    }

}
