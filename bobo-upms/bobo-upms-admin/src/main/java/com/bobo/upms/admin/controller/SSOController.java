package com.bobo.upms.admin.controller;



import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bobo.common.base.BaseController;
import com.bobo.common.util.MD5Util;
import com.bobo.common.util.RedisUtil;
import com.bobo.common.util.StringUtil;
import com.bobo.upms.client.constant.UpmsResult;
import com.bobo.upms.client.constant.UpmsResultConstant;
import com.bobo.upms.rpc.api.IUpmsApiService;
import com.bobo.upms.rpc.api.IUpmsSystemService;
import com.bobo.upms.rpc.pojo.UpmsSystem;
import com.bobo.upms.rpc.pojo.UpmsUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by huabo on 2017/5/27.
 */
@Controller
@RequestMapping("/sso")
@Api(value = "单点登录管理", description = "单点登录管理")
public class SSOController extends BaseController{

    private final static Logger _log = LoggerFactory.getLogger(SSOController.class);

    // 全局会话key
    private final static String BOBO_UPMS_SERVER_SESSION_ID = "bobo-upms-server-session-id";
    // 全局会话key列表
    private final static String BOBO_UPMS_SERVER_SESSION_IDS = "bobo-upms-server-session-ids";
    // code key
    private final static String BOBO_UPMS_SERVER_CODE = "bobo-upms-server-code";




    @Autowired
    IUpmsSystemService upmsSystemService;

    @Autowired
    private IUpmsApiService upmsApiService;



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
    @Login(action = Action.Skip)
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        SSOToken st = SSOHelper.getToken(request);
        if(st != null){
            return redirectTo("/manage/index");
        }
        return "sso/login";
    }


    @ApiOperation(value = "登录")
    @Login(action = Action.Skip)
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response){
        /**
         * 过滤 XSS SQL 注入
         */
        WafRequestWrapper wr = new WafRequestWrapper(request);
        String username = wr.getParameter("username");
        String password = wr.getParameter("password");
        String rememberMe = wr.getParameter("rememberMe");
        if (StringUtils.isEmpty(username)){
            return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME,"账号不能为空");
        }
        if (StringUtils.isEmpty(password)){
            return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD,"密码不能为空");
        }
        //查询用户信息
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
        if(null == upmsUser){
            return new UpmsResult(UpmsResultConstant.INVALID_USERNAME,"该用户不存在");
        }
        if(!upmsUser.getPassword().equals(MD5Util.MD5(password+upmsUser.getSalt()))){
            return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD,"密码错误");
        }
        if(upmsUser.getLocked() == 1){
            return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT,"用户被锁定");
        }

        SSOToken st = new SSOToken(request);
        st.setId(upmsUser.getUserId());
        st.setData(username);
        if(BooleanUtils.toBoolean(rememberMe)){
            request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
        }
        SSOHelper.setSSOCookie(request, response, st, true);
        //回跳登录前地址
        String backUrl = request.getParameter("ReturnURL");
        if(StringUtils.isEmpty(backUrl)){
            return new UpmsResult(UpmsResultConstant.SUCCESS,"/");
        }else {
            try {
                backUrl = URLDecoder.decode(backUrl,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                _log.error("ReturnURL解析报错");
                backUrl = "/";
            }
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
    @Permission(action = Action.Skip)
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
//        //shrio退出登录
//        SecurityUtils.getSubject().logout();
        //kisso 退出登录
        SSOHelper.clearLogin(request, response);
        //跳回原地址
        String redirectUrl = request.getHeader("Referer");
        if(null == redirectUrl){
            redirectUrl = "/";
        }
        return "redirect:" +redirectUrl;
    }


}
