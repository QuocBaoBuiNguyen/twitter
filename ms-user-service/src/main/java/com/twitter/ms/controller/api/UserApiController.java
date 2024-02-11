package com.twitter.ms.controller.api;

import com.gmail.merikbest2015.dto.response.user.UserPrincipalResponse;
import com.twitter.ms.constants.ApiConstants;
import com.twitter.ms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApiConstants.MICROSERVICE_PREFIX)
@RequiredArgsConstructor
public class UserApiController {

}
