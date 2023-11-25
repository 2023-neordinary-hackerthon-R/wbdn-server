package neordinaryr.wbdn.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtTokenProvider {


    private final SecretKey secretKey;
    private final long accessTokenValiditySeconds;

    public JwtTokenProvider(
            @Value("${jwt.secret}") final String secretKey,
            @Value("${jwt.token-validity-in-seconds}") final long accessTokenValiditySeconds
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public String createAccessToken(Long memberId) {
        return createToken(memberId, accessTokenValiditySeconds);
    }

    private String createToken(Long memberId, long validitySeconds) {
        Claims claims = Jwts.claims();
        claims.put("sub", memberId);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(validitySeconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Long getId(String token) {
        return getClaims(token).getBody()
                .get("sub", Long.class);


    }

    public boolean isTokenValid(String token) {
        try {
            Jws<Claims> claims = getClaims(token);
            Date expiredDate = claims.getBody()
                    .getExpiration();
            Date now = new Date();
            return expiredDate.after(now);
        } catch (ExpiredJwtException e) {
            throw BaseException.of(ErrorCode.AUTH_EXPIRED_TOKEN);
        } catch (SecurityException
                 | MalformedJwtException
                 | UnsupportedJwtException
                 | IllegalArgumentException e) {
            throw BaseException.of(ErrorCode.AUTH_INVALID_TOKEN);
        }
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}