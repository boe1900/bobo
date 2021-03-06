package com.bobo.upms.rpc.api;

import com.baomidou.mybatisplus.service.IService;
import com.bobo.upms.rpc.pojo.UpmsUserRole;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public interface IUpmsUserRoleService extends IService<UpmsUserRole> {
    /**
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);
}
