package com.bobo.upms.admin.controller.manage;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bobo.upms.client.constant.UpmsResult;
import com.bobo.upms.client.constant.UpmsResultConstant;
import com.bobo.upms.rpc.api.IUpmsLogService;
import com.bobo.upms.rpc.pojo.UpmsLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huabo on 2017/6/12.
 */
@Controller
@Api(value = "日志管理", description = "日志管理")
@RequestMapping("/manage/log")
public class UpmsLogController {
    private static Logger _log = LoggerFactory.getLogger(UpmsLogController.class);
    private static final String SEARCH_COLUMN = "title";


    @Resource
    private IUpmsLogService upmsLogService;

    @ApiOperation(value = "日志首页")
    @Permission("upms:log:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/log/index";
    }

    @ApiOperation(value = "日志列表")
    @Permission("upms:log:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        int current = offset / limit+1;
        Page<UpmsLog> page = new Page<UpmsLog>(current,limit,sort);
        page.setAsc("asc".equals(order));

        EntityWrapper<UpmsLog> ew = new EntityWrapper<>();
        ew.like(SEARCH_COLUMN,search);



        Page<UpmsLog> upmsPermissions = upmsLogService.selectPage(page,ew);


        Map<String, Object> result = new HashMap<>();
        result.put("rows", upmsPermissions.getRecords());
        result.put("total", upmsPermissions.getTotal());
        return result;
    }

    @ApiOperation(value = "删除日志")
    @Permission("upms:log:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        List<String> idList = Arrays.asList(ids.split("-"));

        boolean isSuccess = upmsLogService.deleteBatchIds(idList);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }


}
