package com.bobo.upms.admin.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bobo.common.validator.LengthValidator;
import com.bobo.upms.client.constant.UpmsResult;
import com.bobo.upms.client.constant.UpmsResultConstant;
import com.bobo.upms.rpc.api.IUpmsPermissionService;
import com.bobo.upms.rpc.api.IUpmsSystemService;
import com.bobo.upms.rpc.pojo.UpmsPermission;

import com.bobo.upms.rpc.pojo.UpmsSystem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限controller
 * Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "权限管理", description = "权限管理")
@RequestMapping("/manage/permission")
public class UpmsPermissionController  {

    private static Logger _log = LoggerFactory.getLogger(UpmsPermissionController.class);
    private static final String SEARCH_COLUMN = "name";


    @Resource
    private IUpmsPermissionService upmsPermissionService;

    @Resource
    private IUpmsSystemService upmsSystemService;



    @ApiOperation(value = "权限首页")
    @Permission("upms:permission:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/permission/index";
    }
    @ApiOperation(value = "权限列表")
    @Permission("upms:permission:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, defaultValue = "0", value = "type") int type,
            @RequestParam(required = false, defaultValue = "0", value = "systemId") int systemId,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        int current = offset / limit+1;
        Page<UpmsPermission> page = new Page<UpmsPermission>(current,limit,sort);
        page.setAsc("asc".equals(order));

        EntityWrapper<UpmsPermission> ew = new EntityWrapper<>();
        ew.like(SEARCH_COLUMN,search);

        if(0 != type){
            ew.eq("type",type);
        }
        if(0 != systemId){
            ew.eq("system_id",systemId);
        }

        Page<UpmsPermission> upmsPermissions = upmsPermissionService.selectPage(page,ew);


        Map<String, Object> result = new HashMap<>();
        result.put("rows", upmsPermissions.getRecords());
        result.put("total", upmsPermissions.getTotal());
        return result;
    }





    @ApiOperation(value = "角色权限列表")
    @Permission("upms:permission:read")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id) {
        return upmsPermissionService.getTreeByRoleId(id);
    }
    @ApiOperation(value = "用户权限列表")
    @Permission("upms:permission:read")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object user(@PathVariable("id") int id, HttpServletRequest request) {
        return upmsPermissionService.getTreeByUserId(id, NumberUtils.toInt(request.getParameter("type")));
    }



    @ApiOperation(value = "新增权限")
    @Permission("upms:permission:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        EntityWrapper<UpmsSystem> ew = new EntityWrapper<>();
        ew.eq("status",1);
        List<UpmsSystem> upmsSystems = upmsSystemService.selectList(ew);
        modelMap.put("upmsSystems", upmsSystems);
        return "/manage/permission/create";
    }

    @ApiOperation(value = "新增权限")
    @Permission("upms:permission:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsPermission upmsPermission) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsPermission.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        upmsPermission.setCtime(time);
        upmsPermission.setOrders(time);
        boolean isSuccess = upmsPermissionService.insert(upmsPermission);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }

    @ApiOperation(value = "删除权限")
    @Permission("upms:permission:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        List<String> idList = Arrays.asList(ids.split("-"));

        boolean isSuccess = upmsPermissionService.deleteBatchIds(idList);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }

    @ApiOperation(value = "修改权限")
    @Permission("upms:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        EntityWrapper<UpmsSystem> ew = new EntityWrapper<>();
        ew.eq("status",1);
        List<UpmsSystem> upmsSystems = upmsSystemService.selectList(ew);
        UpmsPermission permission = upmsPermissionService.selectById(id);
        modelMap.put("permission", permission);
        modelMap.put("upmsSystems", upmsSystems);
        return "/manage/permission/update";
    }

    @ApiOperation(value = "修改权限")
    @Permission("upms:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsPermission upmsPermission) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsPermission.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsPermission.setPermissionId(id);
        boolean isSuccess = upmsPermissionService.updateById(upmsPermission);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }











}
