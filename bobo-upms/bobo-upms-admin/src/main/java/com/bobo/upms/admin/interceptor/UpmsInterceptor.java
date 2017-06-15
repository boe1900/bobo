package com.bobo.upms.admin.interceptor;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.bobo.upms.rpc.api.IUpmsApiService;

import com.bobo.upms.rpc.api.IUpmsUserService;
import com.bobo.upms.rpc.pojo.UpmsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录信息拦截器
 * Created by shuzheng on 2017/2/11.
 */
public class UpmsInterceptor extends HandlerInterceptorAdapter {

    private static Logger _log = LoggerFactory.getLogger(UpmsInterceptor.class);

//    @Resource
//    IUpmsApiService upmsApiService;

    @Resource
    IUpmsUserService upmsUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 过滤ajax
        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        }
        // 登录信息
        SSOToken st = SSOHelper.getToken(request);
        if(st != null){
            UpmsUser upmsUser = upmsUserService.selectById(st.getId());
            request.setAttribute("upmsUser", upmsUser);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}
