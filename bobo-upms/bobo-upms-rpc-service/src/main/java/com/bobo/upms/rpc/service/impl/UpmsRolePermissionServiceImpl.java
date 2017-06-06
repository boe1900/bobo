package com.bobo.upms.rpc.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.upms.rpc.api.IUpmsRolePermissionService;
import com.bobo.upms.rpc.dao.mapper.UpmsRolePermissionMapper;
import com.bobo.upms.rpc.pojo.UpmsRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public class UpmsRolePermissionServiceImpl extends ServiceImpl<UpmsRolePermissionMapper, UpmsRolePermission> implements IUpmsRolePermissionService {

    @Autowired
    private UpmsRolePermissionMapper upmsRolePermissionMapper;


    @Transactional
    @Override
    public int rolePermission(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for(int i=0;i<datas.size();i++){
            JSONObject jsonObject = datas.getJSONObject(i);
            if(!jsonObject.getBoolean("checked")){
                deleteIds.add(jsonObject.getIntValue("id"));
            } else {
                //新增权限
                UpmsRolePermission upmsRolePermission = new UpmsRolePermission();
                upmsRolePermission.setRoleId(id);
                upmsRolePermission.setPermissionId(jsonObject.getIntValue("id"));
                upmsRolePermissionMapper.insert(upmsRolePermission);
            }
        }
        //删除权限
        if(deleteIds.size()>0){
            EntityWrapper<UpmsRolePermission> ew = new EntityWrapper<>();
            ew.eq("role_id",id).in("permission_id",deleteIds);
            upmsRolePermissionMapper.delete(ew);
        }

        return datas.size();
    }
}
