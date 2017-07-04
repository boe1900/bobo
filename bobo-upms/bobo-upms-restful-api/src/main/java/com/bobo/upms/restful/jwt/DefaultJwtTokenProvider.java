package com.bobo.upms.restful.jwt;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.bobo.upms.restful.jwt.model.AccessToken;
import com.bobo.upms.restful.constant.ApiCode;
import com.bobo.upms.restful.constant.ApiResult;
import com.bobo.upms.restful.jwt.model.TokenType;
import com.bobo.upms.restful.jwt.model.UserContext;
import com.bobo.upms.restful.jwt.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huabo on 2017/7/4.
 */
public class DefaultJwtTokenProvider implements JwtTokenProvider {

    private static Logger logger = LoggerFactory.getLogger(DefaultJwtTokenProvider.class);



    @Override
    public boolean doVerifyAccessTokenProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String auth = request.getHeader("Authorization");

        String token = JwtTokenUtil.extractAuthorizationHeader(auth);

        Jws<Claims> claimsJws = JwtTokenUtil.parseClaims(token);

        if(claimsJws != null){
            Claims claims = claimsJws.getBody();
            String tokenType = String.valueOf(claims.get("tokenType"));
            if(TokenType.ACCESS_TOKEN.equals(tokenType)){
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String ret = JSONObject.toJSON(new ApiResult(ApiCode.INVALID_TOKEN,null)).toString();
        response.getWriter().write(ret);
        return false;
    }

    @Override
    public String doVerifyRefreshTokenProcess(HttpServletRequest request, HttpServletResponse response)  {
        WafRequestWrapper wr = new WafRequestWrapper(request);
        String refresh_token = wr.getParameter("refresh_token");
        if(StringUtils.isNotBlank(refresh_token)){
            Jws<Claims> claimsJws = JwtTokenUtil.parseClaims(refresh_token);
            if(claimsJws != null){
                Claims claims = claimsJws.getBody();
                String tokenType = String.valueOf(claims.get("tokenType"));
                if(TokenType.REFRESH_TOKEN.equals(tokenType)){
                    return claims.getSubject();
                }
            }
        }
        return null;
    }

    @Override
    public AccessToken getToken(UserContext userContext) {

        AccessToken accessToken = new AccessToken();

        String accessTokenStr = JwtTokenUtil.createAccessJwtToken(userContext);

        String refreshTokenStr = JwtTokenUtil.createRefreshJwtToken(userContext);

        accessToken.setAccess_token(accessTokenStr);

        accessToken.setRefresh_token(refreshTokenStr);

        accessToken.setExpires_in(JwtTokenUtil.getTokenExpirationTime());

        accessToken.setToken_type("Bearer");

        return accessToken;
    }
}
