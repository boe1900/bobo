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
import com.bobo.upms.rpc.api.IUpmsSystemService;
import com.bobo.upms.rpc.pojo.UpmsSystem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
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
    @Permission("upms:system:read")
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "manage/system/index";
    }

    @ApiOperation(value = "系统列表")
    @Permission("upms:system:read")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false,defaultValue = "0",value = "offset") int offset,
            @RequestParam(required = false,defaultValue = "10",value = "limit") int limit,
            @RequestParam(required = false,defaultValue = "",value = "search") String search,
            @RequestParam(required = false,value = "sort") String sort,
            @RequestParam(required = false,value = "order") String order){
        int current = offset / limit+1;
        Page<UpmsSystem> page = new Page<UpmsSystem>(current,limit,sort);
        page.setAsc("asc".equals(order));

        EntityWrapper<UpmsSystem> ew = new EntityWrapper<UpmsSystem>();
        ew.like(SEARCH_COLUMN,search);

        Page<UpmsSystem> upmsSystems = upmsSystemService.selectPage(page,ew);

        Map<String, Object> result = new HashMap<>();
        result.put("rows",upmsSystems.getRecords());
        result.put("total",upmsSystems.getTotal());
        return result;
    }

    @ApiOperation(value = "新增系统")
    @Permission("upms:system:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/system/create";
    }

    @ApiOperation(value = "新增系统")
    @Permission("upms:system:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(UpmsSystem upmsSystem) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
                .on(upmsSystem.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        upmsSystem.setCtime(time);
        upmsSystem.setOrders(time);
        boolean isSuccess = upmsSystemService.insertAllColumn(upmsSystem);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }

    @ApiOperation(value = "删除系统")
    @Permission("upms:system:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        String[] idArr = ids.split("-");
        List<String> idList = Arrays.asList(idArr);
        boolean isSuccess = upmsSystemService.deleteBatchIds(idList);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }

    @ApiOperation(value = "修改系统")
    @Permission("upms:system:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsSystem system = upmsSystemService.selectById(id);
        modelMap.put("system", system);
        return "/manage/system/update";
    }

    @ApiOperation(value = "修改系统")
    @Permission("upms:system:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsSystem upmsSystem) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
                .on(upmsSystem.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsSystem.setSystemId(id);
        boolean isSuccess = upmsSystemService.updateById(upmsSystem);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }




}
