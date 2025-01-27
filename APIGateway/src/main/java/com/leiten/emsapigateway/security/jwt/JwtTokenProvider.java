package com.leiten.emsapigateway.security.jwt;

import static com.leiten.emsapigateway.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.leiten.emsapigateway.constants.SecurityConstants.BEARER_PREFIX;

import java.util.Base64;
import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.leiten.emsapigateway.constants.ErrorMessage.TokenInvalid;
import com.leiten.emsapigateway.exceptionHandler.UnauthorisedException;
import com.leiten.emsapigateway.security.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;



@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final JwtProperties jwtProperties;

    private final CustomUserDetailsService userDetailsService;

    private String secretKey;

    public JwtTokenProvider(JwtProperties jwtProperties, CustomUserDetailsService userDetailsService) {
        this.jwtProperties = jwtProperties;
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION_HEADER);

        return (!Objects.isNull(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) ?
                bearerToken.substring(7, bearerToken.length()) : null;
    }

    public boolean validateToken(String token) {

        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return (!claims.getBody().getExpiration().before(new Date()));
        } catch (JwtException | IllegalArgumentException e) {
            LOGGER.error("Expired or invalid JWT token");
            throw new UnauthorisedException(TokenInvalid.MESSAGE, TokenInvalid.DEVELOPER_MESSAGE);
        }
    }
}
