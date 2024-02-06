package com.twitter.ms.controller;

import com.twitter.ms.constants.ApiConstants;
import com.twitter.ms.dto.request.AuthRequest;
import com.twitter.ms.dto.request.RegistrationEmailCodeProcessRequest;
import com.twitter.ms.dto.request.RegistrationRequest;
import com.twitter.ms.dto.response.AuthResponse;
import com.twitter.ms.dto.response.RegistrationResponse;
import com.twitter.ms.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiConstants.APPLICATION_PREFIX + "/auth")
public class AuthController {

    private final RegistrationService registrationService;

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> loginController(
             @RequestHeader HttpHeaders headers,
             @RequestBody AuthRequest authRequest) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path="/register/check")
    public ResponseEntity<RegistrationResponse> registerValidateController(
            @RequestHeader HttpHeaders headers,
           @RequestBody RegistrationRequest userRegisterRequest) {

        RegistrationResponse response = registrationService.registrationValidateService(userRegisterRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path="/register/code")
    public ResponseEntity<RegistrationResponse> sendRegistrationCode(
            @RequestHeader HttpHeaders headers,
            @RequestBody RegistrationEmailCodeProcessRequest registrationEmailCodeProcessRequest) {
        registrationService.sendRegistrationCode(registrationEmailCodeProcessRequest.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
