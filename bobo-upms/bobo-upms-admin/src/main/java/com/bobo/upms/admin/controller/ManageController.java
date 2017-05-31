package com.bobo.upms.admin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bobo.upms.rpc.api.IUpmsApiService;
import com.bobo.upms.rpc.api.IUpmsSystemService;
import com.bobo.upms.rpc.pojo.UpmsPermission;
import com.bobo.upms.rpc.pojo.UpmsSystem;
import com.bobo.upms.rpc.pojo.UpmsUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by huabo on 2017/5/27.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController {
    private static Logger _log = LoggerFactory.getLogger(ManageController.class);

    @Resource
    private IUpmsSystemService upmsSystemService;

    @Resource
    private IUpmsApiService upmsApiService;


    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        EntityWrapper<UpmsSystem> wrapper = new EntityWrapper<>();
        UpmsSystem condition = new UpmsSystem();
        condition.setStatus(1);
        wrapper.setEntity(condition);
        List<UpmsSystem> upmsSystems = upmsSystemService.selectList(wrapper);
        modelMap.put("upmsSystems",upmsSystems);
        //当前登录用户
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);

        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());

        modelMap.put("upmsPermissions",upmsPermissions);

        return "manage/index";
    }



}
