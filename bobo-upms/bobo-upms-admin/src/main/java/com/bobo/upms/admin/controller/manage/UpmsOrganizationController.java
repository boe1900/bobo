package com.bobo.upms.admin.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollector;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bobo.common.validator.LengthValidator;
import com.bobo.common.validator.NotNullValidator;
import com.bobo.upms.client.constant.UpmsResult;
import com.bobo.upms.client.constant.UpmsResultConstant;
import com.bobo.upms.rpc.api.IUpmsOrganizationService;
import com.bobo.upms.rpc.pojo.UpmsOrganization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by huabo on 2017/6/2.
 */
@Controller
@Api(value = "组织管理",description = "组织管理")
@RequestMapping("/manage/organization")
public class UpmsOrganizationController {

    private static final Logger _log = LoggerFactory.getLogger(UpmsOrganizationController.class);

    private static final String SEARCH_COLUMN = "title";

    @Resource
    private IUpmsOrganizationService upmsOrganizationService;


    @ApiOperation(value = "组织首页")
    @Permission("upms:organization:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "manage/organization/index";
    }

    @ApiOperation(value = "组织列表")
    @Permission("upms:organization:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        int current = offset / limit+1;
        Page<UpmsOrganization> page = new Page<UpmsOrganization>(current,limit,sort);
        page.setAsc("asc".equals(order));
        EntityWrapper<UpmsOrganization> ew = new EntityWrapper<UpmsOrganization>();
        ew.like(SEARCH_COLUMN,search);
        Page<UpmsOrganization> upmsOrganizations = upmsOrganizationService.selectPage(page,ew);
        Map<String, Object> result = new HashMap<>();
        result.put("rows",upmsOrganizations.getRecords());
        result.put("total",upmsOrganizations.getTotal());
        return result;
    }

    @ApiOperation(value = "新增组织")
    @Permission("upms:organization:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "manage/organization/create";
    }




    @ApiOperation(value = "新增组织")
    @Permission("upms:organization:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsOrganization upmsOrganization) {
        //字段校验
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsOrganization.getName(),new LengthValidator(1,20,"名称"))
                .doValidate().result(ResultCollectors.toComplex());
        if(!result.isSuccess()){
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH,result.getErrors());
        }
        long time = System.currentTimeMillis();
        upmsOrganization.setCtime(time);
        boolean isSuccess = upmsOrganizationService.insertAllColumn(upmsOrganization);
        return new UpmsResult( isSuccess ? UpmsResultConstant.SUCCESS:UpmsResultConstant.FAILED,isSuccess);
    }

    @ApiOperation(value = "删除组织")
    @Permission("upms:organization:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        String[] idArr = ids.split("-");
        List<String> idList = Arrays.asList(idArr);
        boolean isSuccess = upmsOrganizationService.deleteBatchIds(idList);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS  : UpmsResultConstant.FAILED, isSuccess);
    }


    @ApiOperation(value = "修改组织")
    @Permission("upms:organization:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsOrganization organization = upmsOrganizationService.selectById(id);
        modelMap.put("organization", organization);
        return "manage/organization/update";
    }

    @ApiOperation(value = "修改组织")
    @Permission("upms:organization:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsOrganization upmsOrganization) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsOrganization.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsOrganization.setOrganizationId(id);
        boolean isSuccess = upmsOrganizationService.updateById(upmsOrganization);
        return new UpmsResult(isSuccess ? UpmsResultConstant.SUCCESS : UpmsResultConstant.FAILED, isSuccess);
    }

}
