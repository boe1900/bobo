package com.bobo.upms.client.shiro.realm;

import com.bobo.common.util.MD5Util;
import com.bobo.common.util.PropertiesFileUtil;
import com.bobo.upms.rpc.api.IUpmsApiService;
import com.bobo.upms.rpc.api.IUpmsSystemService;
import com.bobo.upms.rpc.pojo.UpmsUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by huabo on 2017/5/27.
 */
public class UpmsRealm extends AuthorizingRealm {


    @Resource
    private IUpmsApiService upmsApiService;

    /**
     * 授权：验证权限时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * 认证：登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        // client无密认证
        String upmsType = PropertiesFileUtil.getInstance("bobo-upms-client").get("upms.type");
        if("client".equals(upmsType)){
            return new SimpleAuthenticationInfo(username,password,getName());
        }
        //查询用户信息
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);

        if(null == upmsUser){
            throw new UnknownAccountException();
        }

        if(!upmsUser.getPassword().equals(MD5Util.MD5(password+upmsUser.getSalt()))){
            throw new IncorrectCredentialsException();
        }

        if(upmsUser.getLocked() == 1){
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
