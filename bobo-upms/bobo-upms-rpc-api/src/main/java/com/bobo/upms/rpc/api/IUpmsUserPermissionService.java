package com.bobo.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.bobo.upms.rpc.pojo.UpmsUserPermission;

/**
 * <p>
 * 用户权限关联表 服务类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public interface IUpmsUserPermissionService extends IService<UpmsUserPermission> {
    /**
     * 用户权限
     * @param datas 权限数据
     * @param id 用户id
     * @return
     */
    int permission(JSONArray datas, int id);
}
