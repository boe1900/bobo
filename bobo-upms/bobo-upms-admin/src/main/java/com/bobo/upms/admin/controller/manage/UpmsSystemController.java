package com.bobo.upms.admin.controller.manage;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bobo.upms.rpc.api.IUpmsSystemService;
import com.bobo.upms.rpc.pojo.UpmsSystem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huabo on 2017/6/1.
 */
@Api(value = "系统管理",description = "系统管理")
@Controller
@RequestMapping("/manage/system")
public class UpmsSystemController {
    private static Logger _log = LoggerFactory.getLogger(UpmsSystemController.class);
    private static final String SEARCH_COLUMN = "title";
    @Resource
    private IUpmsSystemService upmsSystemService;


    @ApiOperation(value = "系统首页")
    @RequiresPermissions("upms:system:read")
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "manage/system/index";
    }

    @ApiOperation(value = "系统列表")
    @RequiresPermissions("upms:system:read")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false,defaultValue = "0",value = "offset") int offset,
            @RequestParam(required = false,defaultValue = "10",value = "limit") int limit,
            @RequestParam(required = false,defaultValue = "",value = "search") String search,
            @RequestParam(required = false,value = "sort") String sort,
            @RequestParam(required = false,value = "order") String order){

        Page<UpmsSystem> page = new Page<UpmsSystem>(offset+1,limit,sort);
        page.setAsc("asc".equals(order));

        EntityWrapper<UpmsSystem> ew = new EntityWrapper<UpmsSystem>();
        ew.like(SEARCH_COLUMN,search);

        Page<UpmsSystem> upmsSystems = upmsSystemService.selectPage(page,ew);

        Map<String, Object> result = new HashMap<>();
        result.put("rows",upmsSystems.getRecords());
        result.put("total",upmsSystems.getTotal());
        return result;
    }






}
