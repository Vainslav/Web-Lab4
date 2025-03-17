package javagnomes.lab4.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class JwtManager {
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("SECRETNAYAINFA");

    public static String createToken(String username, String password) {
        return JWT.create()
                .withClaim("username", username)
                .withClaim("password", password)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .withIssuer("auth0")
                .sign(ALGORITHM);
    }

    public static String getUsernameFromToken(String token) {
        try{
            token = token.replace("Bearer ", "");
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer("auth0")
                    .build();
            return verifier.verify(token).getClaim("username").asString();
        } catch (JWTVerificationException e) {
            throw e;
        }
    }
}
