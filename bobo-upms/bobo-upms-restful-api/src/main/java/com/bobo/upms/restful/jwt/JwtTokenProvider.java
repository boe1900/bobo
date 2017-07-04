package com.bobo.upms.restful.jwt;

import com.bobo.upms.restful.jwt.model.AccessToken;
import com.bobo.upms.restful.jwt.model.UserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huabo on 2017/7/4.
 */
public interface JwtTokenProvider {

    boolean doVerifyAccessTokenProcess(HttpServletRequest request,HttpServletResponse response) throws IOException;

    String doVerifyRefreshTokenProcess(HttpServletRequest request,HttpServletResponse response) ;

    AccessToken getToken(UserContext userContext);

}
