package com.bobo.upms.restful.constant;

/**
 * Created by huabo on 2017/6/21.
 */
public enum ApiCode {

    OK(200, "ok"), // 正确请求
    INVALID_REQUEST(40012, "invalid request"), // 非法请求
    PERMISSION_DENIED(40013, "permission denied"), // 无权限
    INVALID_APPID(40014, "invalid appid"), // AppID无效错误


    INVALID_LENGTH(40015, "Invalid length"),
    EMPTY_USERNAME(40016, "Username cannot be empty"),
    EMPTY_PASSWORD(40017, "Password cannot be empty"),
    INVALID_USERNAME(40018, "Account does not exist"),
    INVALID_PASSWORD(40019, "Password error"),
    INVALID_ACCOUNT(40020, "Invalid account"),
    INVALID_TOKEN(40021, "Invalid token"),
    EXPIRED_TOKEN(40022,"Token expired"),
    INTERNAL_SERVER_ERROR(50000, "Internal server error");


    /** 主键 */
    private final int code;

    /** 描述 */
    private final String desc;

    ApiCode(final int code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
