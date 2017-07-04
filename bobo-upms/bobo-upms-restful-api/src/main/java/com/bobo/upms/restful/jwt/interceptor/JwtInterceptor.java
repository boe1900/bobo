package com.bobo.upms.restful.jwt.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.bobo.upms.restful.constant.ApiCode;
import com.bobo.upms.restful.constant.ApiResult;
import com.bobo.upms.restful.jwt.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by huabo on 2017/6/22.
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private JwtInterceptor jwtInterceptor;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Login login = method.getAnnotation(Login.class);
            if (login != null) {
                if (login.action() == Action.Skip) {
                    return true;
                }
            }

            String auth = request.getHeader("Authorization");

            String token = JwtTokenUtil.extractAuthorizationHeader(auth);

            Jws<Claims> claimsJws = JwtTokenUtil.parseClaims(token);

            if(claimsJws != null){
                return true;
            }else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                String ret = JSONObject.toJSON(new ApiResult(ApiCode.INVALID_TOKEN,null)).toString();
                response.getWriter().write(ret);
                return false;
            }
        }

        return true;
    }

    public void setJwtInterceptor(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }
}
