package com.bobo.upms.rpc.service.impl;

import com.bobo.upms.rpc.api.IUpmsApiService;
import com.bobo.upms.rpc.dao.mapper.UpmsApiMapper;
import com.bobo.upms.rpc.dao.mapper.UpmsUserMapper;
import com.bobo.upms.rpc.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huabo on 2017/5/27.
 */
public class UpmsApiServiceImpl implements IUpmsApiService {

    private static Logger _log = LoggerFactory.getLogger(UpmsApiServiceImpl.class);

    @Autowired
    private UpmsUserMapper upmsUserMapper;


    @Autowired
    private UpmsApiMapper upmsApiMapper;



    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        //用户不存在或者锁定状态
        UpmsUser upmsUser = upmsUserMapper.selectById(upmsUserId);
        if(upmsUser == null || 1 == upmsUser.getLocked()){
            _log.info("selectUpmsPermissionByUpmsUserId : upmsUserId={}",upmsUserId);
            return  null;
        }

        return upmsApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        //用户不存在或者锁定状态
        UpmsUser upmsUser = upmsUserMapper.selectById(upmsUserId);
        if(upmsUser == null || 1 == upmsUser.getLocked()){
            _log.info("selectUpmsRoleByUpmsUserId : upmsUserId={}",upmsUserId);
            return  null;
        }

        return upmsApiMapper.selectUpmsRoleByUpmsUserId(upmsUserId);
    }

    @Override
    public List<UpmsRolePermission> selectUpmsRolePermissionByUpmsRoleId(Integer upmsRoleId) {

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
