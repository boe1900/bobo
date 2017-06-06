package com.bobo.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.bobo.upms.rpc.pojo.UpmsRolePermission;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public interface IUpmsRolePermissionService extends IService<UpmsRolePermission> {
    /**
     * 角色权限
     * @param datas 权限数据
     * @param id 角色id
     * @return
     */
    int rolePermission(JSONArray datas, int id);
}
