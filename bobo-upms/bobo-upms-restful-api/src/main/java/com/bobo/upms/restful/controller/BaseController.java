package com.bobo.upms.restful.controller;

import com.bobo.upms.restful.constant.ApiCode;
import com.bobo.upms.restful.constant.ApiResult;
import com.bobo.upms.restful.jwt.exception.JwtExpiredTokenException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huabo on 2017/6/27.
 */
public class BaseController {

    @ExceptionHandler
    @ResponseBody
    public Object exp(HttpServletRequest request, Exception ex) {
        if(ex instanceof JwtExpiredTokenException){
            return new ApiResult(ApiCode.EXPIRED_TOKEN);
        }
        if(ex instanceof AuthenticationServiceException){
            return new ApiResult(ApiCode.INVALID_TOKEN);
        }
        return new ApiResult(ApiCode.INTERNAL_SERVER_ERROR,ex.getMessage());
    }
}
