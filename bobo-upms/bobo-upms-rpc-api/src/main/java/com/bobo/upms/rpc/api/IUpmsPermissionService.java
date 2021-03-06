package com.bobo.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.bobo.upms.rpc.pojo.UpmsPermission;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public interface IUpmsPermissionService extends IService<UpmsPermission> {
    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer userId, Integer type);
}
