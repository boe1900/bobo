package com.bobo.upms.client.kisso;

import com.baomidou.kisso.SSOAuthorization;
import com.baomidou.kisso.Token;
import com.bobo.upms.rpc.api.IUpmsApiService;
import com.bobo.upms.rpc.pojo.UpmsPermission;
import com.bobo.upms.rpc.pojo.UpmsUserPermission;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by huabo on 2017/6/15.
 */
public class SSOAuthorizationImpl implements SSOAuthorization {

    @Resource
    private IUpmsApiService upmsApiService;

    @Override
    public boolean isPermitted(Token token, String permission) {

        if(StringUtils.isNotBlank(permission)){
            //查询用户所有权限
            List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId((Integer) token.getId());
            if(permission != null){
                for(UpmsPermission upmsPermission:upmsPermissions){
                    if(permission.equals(upmsPermission.getPermissionValue())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
