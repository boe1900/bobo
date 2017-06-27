package com.bobo.upms.restful.jwt.util;

import com.bobo.common.util.PropertiesFileUtil;
import com.bobo.upms.restful.jwt.exception.JwtExpiredTokenException;
import com.bobo.upms.restful.jwt.model.UserContext;
import io.jsonwebtoken.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;


import java.util.Date;


/**
 * Created by huabo on 2017/6/27.
 */
public class JwtTokenUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static String HEADER_PREFIX = "Bearer ";

    public static final PropertiesFileUtil properties ;

    static {
        properties = PropertiesFileUtil.getInstance("jwt");
    }


    public static String createAccessJwtToken(UserContext userContext){
        if(StringUtils.isBlank(userContext.getUserName())){
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }
        if(userContext.getPermissions() == null || userContext.getPermissions().isEmpty()){
            throw new IllegalArgumentException("User doesn't have any permission");
        }

        Claims claims = Jwts.claims().setSubject(userContext.getUserName());
        claims.put("permissions",userContext.getPermissions());

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Integer tokenExpirationTime = properties.getInt("tokenExpirationTime");

        if(tokenExpirationTime == null){
            throw new IllegalArgumentException("tokenExpirationTime not configured");
        }

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(properties.get("tokenIssuer"))
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis+tokenExpirationTime * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, properties.get("tokenSigningKey"))
                .compact();

        return token;

    }


    public static String extractAuthorizationHeader(String header){
        if (StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }

        if (header.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }

        return header.substring(HEADER_PREFIX.length(), header.length());
    }

    public static Jws<Claims> parseClaims(String jwtToken){
        try {
            return Jwts.parser().setSigningKey(properties.get("tokenSigningKey")).parseClaimsJws(jwtToken);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            logger.error("Invalid JWT Token", ex);
            throw new BadCredentialsException("Invalid JWT token: ", ex);

        } catch (ExpiredJwtException expiredEx) {
            logger.info("JWT Token is expired", expiredEx);
            throw new JwtExpiredTokenException("JWT Token expired", expiredEx);
        }
    }


}
