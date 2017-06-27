package com.bobo.upms.restful.constant;

import com.bobo.common.base.BaseResult;

/**
 * Created by huabo on 2017/6/21.
 */
public class ApiResult extends BaseResult {

    public ApiResult(ApiCode apiCode, Object data) {
        super(apiCode.getCode(), apiCode.getDesc(), data);
    }
}
