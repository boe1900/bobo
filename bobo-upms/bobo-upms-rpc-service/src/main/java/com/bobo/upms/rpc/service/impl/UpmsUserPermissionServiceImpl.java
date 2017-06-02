package com.bobo.upms.rpc.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.upms.rpc.api.IUpmsUserPermissionService;
import com.bobo.upms.rpc.dao.mapper.UpmsUserPermissionMapper;
import com.bobo.upms.rpc.pojo.UpmsUserPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户权限关联表 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public class UpmsUserPermissionServiceImpl extends ServiceImpl<UpmsUserPermissionMapper, UpmsUserPermission> implements IUpmsUserPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserPermissionServiceImpl.class);

    @Autowired
    private UpmsUserPermissionMapper upmsUserPermissionMapper;


    @Transactional
    @Override
    public int permission(JSONArray datas, int id) {

        if(datas == null || datas.size()<=0){
            return 0;
        }
        for(int i=0;i<datas.size();i++){
            JSONObject item = datas.getJSONObject(i);
            if(item.getBoolean("checked")){
                //新增权限
                UpmsUserPermission upmsUserPermission = new UpmsUserPermission();
                upmsUserPermission.setUserId(id);
                upmsUserPermission.setPermissionId(item.getIntValue("id"));
                upmsUserPermission.setType(item.getIntValue("type"));
                upmsUserPermissionMapper.insert(upmsUserPermission);
            }else {
                //删除权限
                EntityWrapper<UpmsUserPermission> ew = new EntityWrapper<>();
                ew.eq("user_id",id)
                        .eq("permission_id",item.getIntValue("id"))
                        .eq("type",item.getIntValue("type"));
                upmsUserPermissionMapper.delete(ew);
            }
        }


        return datas.size();
    }
}
