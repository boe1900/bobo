package com.bobo.upms.rpc.dao.mapper;

import com.bobo.upms.rpc.pojo.UpmsPermission;
import com.bobo.upms.rpc.pojo.UpmsRole;

import java.util.List;

/**
 * Created by huabo on 2017/5/31.
 */
public interface UpmsApiMapper {
    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);


    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

}
