package com.bobo.upms.admin.controller;



import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bobo.common.util.RedisUtil;
import com.bobo.common.util.StringUtil;
import com.bobo.upms.client.constant.UpmsResult;
import com.bobo.upms.client.constant.UpmsResultConstant;
import com.bobo.upms.client.shiro.session.UpmsSession;
import com.bobo.upms.client.shiro.session.UpmsSessionDao;
import com.bobo.upms.rpc.api.IUpmsSystemService;
import com.bobo.upms.rpc.pojo.UpmsSystem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by huabo on 2017/5/27.
 */
@Controller
@RequestMapping("/sso")
@Api(value = "单点登录管理", description = "单点登录管理")
public class SSOController {

    private final static Logger _log = LoggerFactory.getLogger(SSOController.class);

    // 全局会话key
    private final static String BOBO_UPMS_SERVER_SESSION_ID = "bobo-upms-server-session-id";
    // 全局会话key列表
    private final static String BOBO_UPMS_SERVER_SESSION_IDS = "bobo-upms-server-session-ids";
    // code key
    private final static String BOBO_UPMS_SERVER_CODE = "bobo-upms-server-code";


    @Autowired
    UpmsSessionDao upmsSessionDao;

    @Autowired
    IUpmsSystemService upmsSystemService;


    @ApiOperation(value = "认证中心首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws Exception {
        String appid = request.getParameter("appid");
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(appid)) {
            throw new RuntimeException("无效访问！");
        }
        // 判断请求认证系统是否注册
        EntityWrapper<UpmsSystem> ew = new EntityWrapper<>();
        ew.eq("name",appid);

        int count = upmsSystemService.selectCount(ew);
        if (0 == count) {
            throw new RuntimeException(String.format("未注册的系统:%s", appid));
        }
        return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String serverSessionId = session.getId().toString();
        //判断是否已经登录
        String code = RedisUtil.get(BOBO_UPMS_SERVER_SESSION_ID+"_"+serverSessionId);
        if(StringUtils.isNotBlank(code)){
            //回跳
            String backurl = request.getParameter("backurl");
            String username = (String) subject.getPrincipal();
            if(StringUtils.isBlank(backurl)){
                backurl = "/";
            }else {
                if (backurl.contains("?")) {
                    backurl += "&upms_code=" + code + "&upms_username=" + username;
                } else {
                    backurl += "?upms_code=" + code + "&upms_username=" + username;
                }
            }
            _log.debug("认证中心帐号通过，带code回跳：{}", backurl);
            return "redirect:"+backurl;
        }

        return "/sso/login";
    }


    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

            if(BooleanUtils.toBoolean(rememberMe)){
                usernamePasswordToken.setRememberMe(true);
            }else {
                usernamePasswordToken.setRememberMe(false);
            }
            try {
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                return  new UpmsResult(UpmsResultConstant.INVALID_USERNAME,"账号不存在");
            }catch (IncorrectCredentialsException e){
                return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD,"密码错误");
            }catch (LockedAccountException e){
                return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT,"账号已锁定");
            }
            //更新session状态
            upmsSessionDao.updateStatus(sessionId, UpmsSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            RedisUtil.lpush(BOBO_UPMS_SERVER_SESSION_IDS,sessionId);
            // 默认验证账号密码正确，创建code
            String code = UUID.randomUUID().toString();
            //全局的会话code
            RedisUtil.set(BOBO_UPMS_SERVER_SESSION_ID+"_"+sessionId,code,(int) subject.getSession().getTimeout() / 1000);

            //code校验值
            RedisUtil.set(BOBO_UPMS_SERVER_CODE+"_"+code,code,(int) subject.getSession().getTimeout() / 1000);
        }
        //回跳登录前地址
        String backUrl = request.getParameter("backurl");
        if(StringUtils.isEmpty(backUrl)){
            return new UpmsResult(UpmsResultConstant.SUCCESS,"/");
        }else {
            return new UpmsResult(UpmsResultConstant.SUCCESS,backUrl);
        }
    }


    @ApiOperation(value = "校验code")
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public Object code(HttpServletRequest request) {
        String codeParam = request.getParameter("code");
        String code = RedisUtil.get(BOBO_UPMS_SERVER_CODE + "_" + codeParam);
        if (StringUtils.isBlank(codeParam) || !codeParam.equals(code)) {
            new UpmsResult(UpmsResultConstant.FAILED, "无效code");
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, code);
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        //shrio退出登录
        SecurityUtils.getSubject().logout();
        //跳回原地址
        String redirectUrl = request.getHeader("Referer");
        if(null == redirectUrl){
            redirectUrl = "/";
        }
        return "redirect:" +redirectUrl;
    }


}
