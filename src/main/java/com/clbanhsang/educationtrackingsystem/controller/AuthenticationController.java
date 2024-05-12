package com.clbanhsang.educationtrackingsystem.controller;

import com.clbanhsang.educationtrackingsystem.dto.request.AuthenticationRequest;
import com.clbanhsang.educationtrackingsystem.dto.request.IntrospectRequest;
import com.clbanhsang.educationtrackingsystem.dto.response.APIResponse;
import com.clbanhsang.educationtrackingsystem.dto.response.AuthenticationResponse;
import com.clbanhsang.educationtrackingsystem.dto.response.IntrospectResponse;
import com.clbanhsang.educationtrackingsystem.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    APIResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        return APIResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    APIResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest introspectRequest)
            throws ParseException, JOSEException {
        var result = authenticationService.introspectResponse(introspectRequest);
        return APIResponse.<IntrospectResponse>builder()
                .result(result).build();
    }
}
