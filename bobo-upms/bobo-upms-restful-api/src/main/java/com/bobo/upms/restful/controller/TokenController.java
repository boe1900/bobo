package com.bobo.upms.restful.controller;


import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.bobo.common.base.BaseController;
import com.bobo.common.util.MD5Util;
import com.bobo.common.util.PropertiesFileUtil;
import com.bobo.upms.restful.constant.AccessToken;
import com.bobo.upms.restful.constant.ApiCode;
import com.bobo.upms.restful.constant.ApiResult;
import com.bobo.upms.restful.jwt.util.JwtTokenUtil;
import com.bobo.upms.restful.jwt.model.UserContext;
import com.bobo.upms.rpc.api.IUpmsApiService;
import com.bobo.upms.rpc.pojo.UpmsPermission;
import com.bobo.upms.rpc.pojo.UpmsUser;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huabo on 2017/6/21.
 */
@Controller
@RequestMapping("/api/token")
@Api(value = "接口认证中心", description = "接口认证中心")
public class TokenController extends BaseController{

    public static final String GRANT_TYPE = "app_credential";

    @Autowired
    private IUpmsApiService upmsApiService;

    @ResponseBody
    @RequestMapping(value="/access_token", method = RequestMethod.POST)
    @Login(action = Action.Skip)
    public Object accessToken() {
        WafRequestWrapper wr = new WafRequestWrapper(request);

        String grant_type = wr.getParameter("grant_type");

        String appId = wr.getParameter("appId");

        if(GRANT_TYPE.equals(grant_type)){
            String username = wr.getParameter("username");
            String password = wr.getParameter("password");
            if (StringUtils.isEmpty(username)){
                return new ApiResult(ApiCode.EMPTY_USERNAME,null);
            }
            if(StringUtils.isEmpty(password)){
                return new ApiResult(ApiCode.EMPTY_PASSWORD,null);
            }
            //查询用户信息
            UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
            if(null == upmsUser){
                return new ApiResult(ApiCode.INVALID_USERNAME,null);
            }
            if(!upmsUser.getPassword().equals(MD5Util.MD5(password+upmsUser.getSalt()))){
                return new ApiResult(ApiCode.INVALID_PASSWORD,null);
            }
            if(upmsUser.getLocked() == 1){
                return new ApiResult(ApiCode.INVALID_ACCOUNT,null);
            }
            //获取用户拥有权限
            List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
            List<String > permissions = new ArrayList<>();
            for(UpmsPermission upmsPermission:upmsPermissions){
                permissions.add(upmsPermission.getPermissionValue());
            }

            String accessJwtToken = JwtTokenUtil.createAccessJwtToken(new UserContext(username,permissions));


            //生成accessToken
            AccessToken  accessToken = new AccessToken();
            accessToken.setAccess_token(accessJwtToken);
            accessToken.setExpires_in(PropertiesFileUtil.getInstance("jwt").getInt("tokenExpirationTime"));
            accessToken.setToken_type("bearer");
            return new ApiResult(ApiCode.OK,accessToken);
        }


        return new ApiResult(ApiCode.INVALID_REQUEST,null);
    }

}
