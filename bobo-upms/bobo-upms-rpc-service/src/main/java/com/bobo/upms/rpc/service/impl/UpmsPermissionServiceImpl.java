package com.bobo.upms.rpc.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.upms.rpc.api.IUpmsPermissionService;
import com.bobo.upms.rpc.api.IUpmsSystemService;
import com.bobo.upms.rpc.dao.mapper.UpmsPermissionMapper;
import com.bobo.upms.rpc.dao.mapper.UpmsRolePermissionMapper;
import com.bobo.upms.rpc.dao.mapper.UpmsSystemMapper;
import com.bobo.upms.rpc.dao.mapper.UpmsUserPermissionMapper;
import com.bobo.upms.rpc.pojo.UpmsPermission;
import com.bobo.upms.rpc.pojo.UpmsRolePermission;
import com.bobo.upms.rpc.pojo.UpmsSystem;
import com.bobo.upms.rpc.pojo.UpmsUserPermission;
import io.swagger.models.auth.In;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public class UpmsPermissionServiceImpl extends ServiceImpl<UpmsPermissionMapper, UpmsPermission> implements IUpmsPermissionService {


    @Autowired
    UpmsSystemMapper upmsSystemMapper;

    @Autowired
    UpmsPermissionMapper upmsPermissionMapper;

    @Autowired
    UpmsUserPermissionMapper upmsUserPermissionMapper;

    @Autowired
    UpmsRolePermissionMapper upmsRolePermissionMapper;


    @Override
    public JSONArray getTreeByRoleId(Integer roleId) {
        //获取角色权限
        EntityWrapper<UpmsRolePermission> upmsRolePermissionEw = new EntityWrapper<>();
        upmsRolePermissionEw.eq("role_id",roleId);
        List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionMapper.selectList(upmsRolePermissionEw);

        JSONArray systems = new JSONArray();
        //系统
        EntityWrapper<UpmsSystem> upmsSystemEw = new EntityWrapper<>();
        upmsSystemEw.orderBy("orders",true);
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectList(upmsSystemEw);
        initUpmsSystems(systems, upmsSystems);

        if(systems.size() > 0){
            for(Object system:systems){
                EntityWrapper<UpmsPermission> upmsPermissionEw = new EntityWrapper<>();
                upmsPermissionEw
                        .eq("status",1)
                        .eq("system_id",((JSONObject)system).getIntValue("id"))
                        .orderBy("orders",true);
                //查询该系统下的所有权限 按照orders升序排序
                List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectList(upmsPermissionEw);
                if(CollectionUtils.isEmpty(upmsPermissions)){
                    continue;
                }
                //目录
                JSONArray folders = new JSONArray();
                //将目录放入系统
                ((JSONObject) system).put("children",folders);
                //装载目录
                for(UpmsPermission upmsPermission:upmsPermissions){
                    //权限类型为目录的
                    if(upmsPermission.getPid() == 0 && upmsPermission.getType() ==1){
                        JSONObject folderNode = new JSONObject();
                        folderNode.put("id",upmsPermission.getPermissionId());
                        folderNode.put("name",upmsPermission.getName());
                        folderNode.put("open",true);
                        //装载用户目录权限
                        for(UpmsRolePermission upmsRolePermission:upmsRolePermissions){
                            if(upmsPermission.getPermissionId().intValue() == upmsRolePermission.getPermissionId().intValue()){
                                folderNode.put("checked",true);
                            }
                        }
                        folders.add(folderNode);


                        //菜单
                        JSONArray menus = new JSONArray();
                        //将菜单放入目录
                        folderNode.put("children",menus);
                        //装载菜单
                        for(UpmsPermission upmsPermission1:upmsPermissions){
                            if(upmsPermission1.getPid().intValue() == folderNode.getIntValue("id")
                                    && upmsPermission1.getType().intValue() == 2){
                                JSONObject menuNode = new JSONObject();
                                menuNode.put("id",upmsPermission1.getPermissionId());
                                menuNode.put("name",upmsPermission1.getName());
                                menuNode.put("open",true);
                                for(UpmsRolePermission upmsRolePermission:upmsRolePermissions){
                                    if(upmsRolePermission.getPermissionId().intValue() == upmsPermission1.getPermissionId().intValue()){
                                        menuNode.put("checked",true);
                                    }
                                }
                                menus.add(menuNode);

                                //按钮
                                JSONArray buttons = new JSONArray();
                                //将按钮放入菜单
                                menuNode.put("children",buttons);
                                for(UpmsPermission upmsPermission2:upmsPermissions){
                                    if(upmsPermission2.getPid().intValue() == menuNode.getIntValue("id")
                                            && upmsPermission2.getType().intValue() == 3){
                                        JSONObject button = new JSONObject();
                                        button.put("id",upmsPermission2.getPermissionId());
                                        button.put("name",upmsPermission2.getName());
                                        button.put("open",true);
                                        for(UpmsRolePermission upmsRolePermission:upmsRolePermissions){
                                            if(upmsRolePermission.getPermissionId().intValue() == upmsPermission2.getPermissionId().intValue()){
                                                button.put("checked",true);
                                            }
                                        }
                                        buttons.add(button);
                                    }
                                }

                            }

                        }

                    }
                }


            }

        }

        return systems;
    }

    private void initUpmsSystems(JSONArray systems, List<UpmsSystem> upmsSystems) {
        for(UpmsSystem upmsSystem:upmsSystems){
            JSONObject node = new JSONObject();
            node.put("id",upmsSystem.getSystemId());
            node.put("name",upmsSystem.getTitle());
            node.put("nocheck",true);
            node.put("open",true);
            systems.add(node);
        }
    }


    @Override
    public JSONArray getTreeByUserId(Integer userId, Integer type) {
        //用户权限
        EntityWrapper<UpmsUserPermission> upmsUserPermissionEw = new EntityWrapper<>();
        upmsUserPermissionEw.eq("user_id",userId).eq("type",type);

        List<UpmsUserPermission> upmsUserPermissions = upmsUserPermissionMapper.selectList(upmsUserPermissionEw);


        JSONArray systems = new JSONArray();
        //系统
        EntityWrapper<UpmsSystem> upmsSystemEw = new EntityWrapper<>();
        upmsSystemEw.orderBy("orders",true);
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectList(upmsSystemEw);
        initUpmsSystems(systems, upmsSystems);
        if(systems.size() > 0){
            for(Object system:systems){
                EntityWrapper<UpmsPermission> upmsPermissionEw = new EntityWrapper<>();
                upmsPermissionEw
                        .eq("status",1)
                        .eq("system_id",((JSONObject)system).getIntValue("id"))
                        .orderBy("orders",true);
                //查询该系统下的所有权限 按照orders升序排序
                List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectList(upmsPermissionEw);
                if(CollectionUtils.isEmpty(upmsPermissions)){
                    continue;
                }
                //目录
                JSONArray folders = new JSONArray();
                //将目录放入系统
                ((JSONObject) system).put("children",folders);
                //装载目录
                for(UpmsPermission upmsPermission:upmsPermissions){
                    //权限类型为目录的
                    if(upmsPermission.getPid() == 0 && upmsPermission.getType() ==1){
                        JSONObject folderNode = new JSONObject();
                        folderNode.put("id",upmsPermission.getPermissionId());
                        folderNode.put("name",upmsPermission.getName());
                        folderNode.put("open",true);
                        //装载用户目录权限
                        for(UpmsUserPermission upmsUserPermission:upmsUserPermissions){
                            if(upmsPermission.getPermissionId().intValue() == upmsUserPermission.getPermissionId().intValue()){
                                folderNode.put("checked",true);
                            }
                        }
                        folders.add(folderNode);


                        //菜单
                        JSONArray menus = new JSONArray();
                        //将菜单放入目录
                        folderNode.put("children",menus);
                        //装载菜单
                        for(UpmsPermission upmsPermission1:upmsPermissions){
                            if(upmsPermission1.getPid().intValue() == folderNode.getIntValue("id")
                                    && upmsPermission1.getType().intValue() == 2){
                                JSONObject menuNode = new JSONObject();
                                menuNode.put("id",upmsPermission1.getPermissionId());
                                menuNode.put("name",upmsPermission1.getName());
                                menuNode.put("open",true);
                                for(UpmsUserPermission upmsUserPermission:upmsUserPermissions){
                                    if(upmsUserPermission.getPermissionId().intValue() == upmsPermission1.getPermissionId().intValue()){
                                        menuNode.put("checked",true);
                                    }
                                }
                                menus.add(menuNode);

                                //按钮
                                JSONArray buttons = new JSONArray();
                                //将按钮放入菜单
                                menuNode.put("children",buttons);
                                for(UpmsPermission upmsPermission2:upmsPermissions){
                                    if(upmsPermission2.getPid().intValue() == menuNode.getIntValue("id")
                                            && upmsPermission2.getType().intValue() == 3){
                                        JSONObject button = new JSONObject();
                                        button.put("id",upmsPermission2.getPermissionId());
                                        button.put("name",upmsPermission2.getName());
                                        button.put("open",true);
                                        for(UpmsUserPermission upmsUserPermission:upmsUserPermissions){
                                            if(upmsUserPermission.getPermissionId().intValue() == upmsPermission2.getPermissionId().intValue()){
                                                button.put("checked",true);
                                            }
                                        }
                                        buttons.add(button);
                                    }
                                }

                            }

                        }

                    }
                }


            }

        }

        return systems;
    }
}
