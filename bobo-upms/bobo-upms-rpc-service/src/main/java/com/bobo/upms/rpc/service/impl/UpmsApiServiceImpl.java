package com.bobo.upms.rpc.service.impl;

import com.bobo.upms.rpc.api.IUpmsApiService;
import com.bobo.upms.rpc.dao.mapper.UpmsUserMapper;
import com.bobo.upms.rpc.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huabo on 2017/5/27.
 */
public class UpmsApiServiceImpl implements IUpmsApiService {

    @Autowired
    private UpmsUserMapper upmsUserMapper;






    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {

        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        return null;
    }

    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        return null;
    }

    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        return null;
    }

    @Override
    public UpmsUser selectUpmsUserByUsername(String username) {
        UpmsUser condition = new UpmsUser();

        condition.setUsername(username);

        return upmsUserMapper.selectOne(condition);
    }

    @Override
    public int insertUpmsLogSelective(UpmsLog record) {
        return 0;
    }
}
