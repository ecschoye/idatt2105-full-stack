package edu.ntnu.idatt2105.ecschoye.backend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import edu.ntnu.idatt2105.ecschoye.backend.model.LoginRequest;
import edu.ntnu.idatt2105.ecschoye.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin
public class TokenController {

    // keyStr is hardcoded here for testing purpose
    // in a real scenario, it should either be stored in a database or injected from the environment
    public static final String keyStr = "testsecrettestsecrettestsecrettestsecrettestsecret";
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);

    private static final Logger logger = Logger.getLogger(TokenController.class.getName());

    @Autowired
    private UserService userService;

    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(final @RequestBody LoginRequest loginRequest) throws Exception {
        // if username and password are valid, issue an access token
        // note that subsequent requests need this token
        logger.info("Received request to generate token for user " + loginRequest.getUsername());
        if (userService.login(loginRequest.getUsername(), loginRequest.getPassword())) {
            logger.info("User " + loginRequest.getUsername() + " is authenticated, issuing token...");
            return generateToken(loginRequest.getUsername());
        }
        logger.info("User " + loginRequest.getUsername() + " is not authenticated, access denied...");
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
    }

    public String generateToken(final String userId) {
        logger.info("Generating token for user " + userId);
        final Instant now = Instant.now();
        final Algorithm hmac512 = Algorithm.HMAC512(keyStr);;
        final JWTVerifier verifier = JWT.require(hmac512).build();
        logger.info("Token generated for user " + userId);
        return JWT.create()
                .withSubject(userId)
                .withIssuer("idatt2105_token_issuer_app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(hmac512);
    }
}
