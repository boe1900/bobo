package com.bobo.upms.restful.bean;

import java.io.Serializable;

/**
 * Created by huabo on 2017/6/28.
 */
public class AccessTokenQuery implements Serializable{

    private static final long serialVersionUID = 3023165077911381010L;

    private String grant_type;

    private String appId;

    private String username;

    private String password;

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
