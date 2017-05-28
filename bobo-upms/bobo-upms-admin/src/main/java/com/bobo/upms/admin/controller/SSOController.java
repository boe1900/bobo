package com.bobo.upms.admin.controller;



import com.bobo.common.util.RedisUtil;
import com.bobo.upms.client.constant.UpmsResult;
import com.bobo.upms.client.constant.UpmsResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huabo on 2017/5/27.
 */
@Controller
@RequestMapping("/sso")
@Api(value = "单点登录管理", description = "单点登录管理")
public class SSOController {

    // 全局会话key
    private final static String BOBO_UPMS_SERVER_SESSION_ID = "bobo-upms-server-session-id";
    // 全局会话key列表
    private final static String BOBO_UPMS_SERVER_SESSION_IDS = "bobo-upms-server-session-ids";
    // code key
    private final static String BOBO_UPMS_SERVER_CODE = "bobo-upms-server-code";


    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {

        return "/sso/login";
    }


    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        if (StringUtils.isEmpty(username)){
            return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME,"账号不能为空");
        }
        if (StringUtils.isEmpty(password)){
            return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD,"密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();

        String hasCode = RedisUtil.get(BOBO_UPMS_SERVER_SESSION_ID+"_"+sessionId);

        if(StringUtils.isEmpty(hasCode)){
            //使用shiro认证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);








        }






        return null;
    }




}
