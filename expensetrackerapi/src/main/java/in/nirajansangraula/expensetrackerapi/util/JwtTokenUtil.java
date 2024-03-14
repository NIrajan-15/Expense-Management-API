package in.nirajansangraula.expensetrackerapi.util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Date;

@Component
public class JwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY = 3600000;
    
    @Value("${jwt.secret}")
    private String secret;
    
    // Generate token for user
    // @param userDetails
    // @return token
    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_TOKEN_VALIDITY);

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC512(secret.getBytes()));

    }

    // Get username from token
    // @param token
    // @return username
    public String getUsernameFromToken(String token){
        if(StringUtils.hasText(token)){
            try{
                return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
            }
            catch(JWTDecodeException e)
            {
                return null;
            }
        }
        return null;
    }

    // Check if token is valid
    // @param token
    // @return boolean
    public boolean isTokenExpired(String token){
        Date expiryDate = JWT.require(Algorithm.HMAC512(secret.getBytes()))
            .build()
            .verify(token)
            .getExpiresAt();
        return expiryDate.before(new Date());
    }

    // Validate token
    // @param token
    // @param userDetails
    // @return boolean
    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}