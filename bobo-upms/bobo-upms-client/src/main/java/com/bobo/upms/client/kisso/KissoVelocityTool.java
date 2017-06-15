package com.bobo.upms.client.kisso;


import com.baomidou.kisso.SSOAuthorization;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;
import org.apache.velocity.tools.config.DefaultKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huabo on 2017/6/15.
 */
@DefaultKey("kisso")
public class KissoVelocityTool {

    private static Logger _log = LoggerFactory.getLogger(KissoVelocityTool.class);


    /**
     * 验证用户是否具备某权限。
     *
     * @param permission
     *        权限名称
     * @return 用户是否具备某权限
     */
    public boolean hasPermission(String permission) throws Exception {
        HttpServletRequest request = HttpHelper.getHttpServletRequest();
        Token token = SSOHelper.attrToken(request);
        if ( token == null ) {
            return false;
        }
        SSOAuthorization ssoAuthorization = SpringContextHolder.getBean(SSOAuthorizationImpl.class);
        return ssoAuthorization.isPermitted(token,permission);
    }

}
