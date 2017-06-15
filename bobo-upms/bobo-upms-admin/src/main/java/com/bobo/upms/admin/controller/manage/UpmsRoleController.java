package com.bobo.upms.admin.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bobo.common.validator.LengthValidator;
import com.bobo.upms.client.constant.UpmsResult;
import com.bobo.upms.client.constant.UpmsResultConstant;
import com.bobo.upms.rpc.api.IUpmsRolePermissionService;
import com.bobo.upms.rpc.api.IUpmsRoleService;
import com.bobo.upms.rpc.pojo.UpmsRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
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
 * 角色controller
 * Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "角色管理", description = "角色管理")
@RequestMapping("/manage/role")
public class UpmsRoleController {

    private static Logger _log = LoggerFactory.getLogger(UpmsRoleController.class);
    private static final String SEARCH_COLUMN = "title";
    @Resource
    private IUpmsRoleService upmsRoleService;

    @Resource
    private IUpmsRolePermissionService upmsRolePermissionService;

    @ApiOperation(value = "角色首页")
    @Permission("upms:role:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/role/index";
    }

    @ApiOperation(value = "角色权限")
    @Permission("upms:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsRole role = upmsRoleService.selectById(id);
        modelMap.put("role", role);
        return "/manage/role/permission";
    }

    @ApiOperation(value = "角色权限")
    @Permission("upms:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = upmsRolePermissionService.rolePermission(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }

    @ApiOperation(value = "角色列表")
    @Permission("upms:role:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        int current = offset / limit+1;
        Page<UpmsRole> page = new Page<UpmsRole>(current,limit,sort);
        page.setAsc("asc".equals(order));

        EntityWrapper<UpmsRole> ew = new EntityWrapper<UpmsRole>();
        ew.like(SEARCH_COLUMN,search);

        Page<UpmsRole> upmsRoles = upmsRoleService.selectPage(page,ew);

        Map<String, Object> result = new HashMap<>();
        result.put("rows", upmsRoles.getRecords());
        result.put("total", upmsRoles.getTotal());
        return result;
    }

    @ApiOperation(value = "新增角色")
    @Permission("upms:role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/role/create";
    }

    @ApiOperation(value = "新增角色")
    @Permission("upms:role:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsRole upmsRole) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(upmsRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        upmsRole.setCtime(time);
        upmsRole.setOrders(time);
        boolean isSuccess = upmsRoleService.insert(upmsRole);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }

    @ApiOperation(value = "删除角色")
    @Permission("upms:role:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        List<String> idList = Arrays.asList(ids.split("-"));
        boolean isSuccess = upmsRoleService.deleteBatchIds(idList);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }

    @ApiOperation(value = "修改角色")
    @Permission("upms:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsRole role = upmsRoleService.selectById(id);
        modelMap.put("role", role);
        return "/manage/role/update";
    }

    @ApiOperation(value = "修改角色")
    @Permission("upms:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsRole upmsRole) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(upmsRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsRole.setRoleId(id);
        boolean isSuccess = upmsRoleService.updateById(upmsRole);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }

}
