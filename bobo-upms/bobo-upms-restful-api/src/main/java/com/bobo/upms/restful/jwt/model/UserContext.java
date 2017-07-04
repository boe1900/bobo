package com.bobo.upms.restful.jwt.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huabo on 2017/6/27.
 */
public class UserContext implements Serializable {

    private static final long serialVersionUID = -525273427111504984L;

    private String userId;

    private String userName;

    public UserContext(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
