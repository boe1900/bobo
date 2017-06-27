package com.bobo.upms.restful.jwt.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huabo on 2017/6/27.
 */
public class UserContext implements Serializable {

    private static final long serialVersionUID = -525273427111504984L;

    private String userName;

    private List<String> permissions;

    public UserContext(String userName, List<String> permissions) {
        this.userName = userName;
        this.permissions = permissions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
