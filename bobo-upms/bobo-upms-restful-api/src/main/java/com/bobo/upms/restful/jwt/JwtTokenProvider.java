package com.bobo.upms.restful.jwt;

import com.bobo.upms.restful.jwt.model.AccessToken;
import com.bobo.upms.restful.jwt.model.UserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huabo on 2017/7/4.
 */
public interface JwtTokenProvider {

    boolean doVerifyAccessTokenProcess(HttpServletRequest request,HttpServletResponse response) throws Exception;

    String doVerifyRefreshTokenProcess(HttpServletRequest request,HttpServletResponse response) throws Exception;

    AccessToken getToken(UserContext userContext);

}
