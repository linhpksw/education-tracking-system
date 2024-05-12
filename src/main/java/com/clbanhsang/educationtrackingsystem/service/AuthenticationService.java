package com.clbanhsang.educationtrackingsystem.service;

import com.clbanhsang.educationtrackingsystem.dto.request.AuthenticationRequest;
import com.clbanhsang.educationtrackingsystem.dto.request.IntrospectRequest;
import com.clbanhsang.educationtrackingsystem.dto.response.AuthenticationResponse;
import com.clbanhsang.educationtrackingsystem.dto.response.IntrospectResponse;
import com.clbanhsang.educationtrackingsystem.exception.AppException;
import com.clbanhsang.educationtrackingsystem.exception.ErrorCode;
import com.clbanhsang.educationtrackingsystem.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    protected static final String SIGNER_KEY =
            "uPisGJb0hOHQJMxBjxsNPLkPY6HYlbMG9Vm/P6JKEDn6DeMQmy9bUYkEHGyiRjVF";


    public IntrospectResponse introspectResponse(IntrospectRequest request) throws ParseException, JOSEException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()  // trả về token đã hết hạn hay chưa.
                .valid(verified && expityTime.after(new Date()))
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        var user = userRepository.findByEmail(authenticationRequest.getEmail());
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(authenticationRequest.getEmail());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }

    private String generateToken(String email) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("education-tracking-system.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("CustomClaim", "custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Can not create token", e);
            throw new RuntimeException(e);
        }
    }
}
