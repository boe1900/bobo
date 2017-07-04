package com.bobo.upms.restful.controller;

import com.bobo.upms.restful.constant.ApiCode;
import com.bobo.upms.restful.constant.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huabo on 2017/6/22.
 */
@RestController
@RequestMapping("/api")
public class TestApiController extends BaseRestController {

    @RequestMapping("test")
    public Object test(){
        return new ApiResult(ApiCode.OK,"success");
    }

}
