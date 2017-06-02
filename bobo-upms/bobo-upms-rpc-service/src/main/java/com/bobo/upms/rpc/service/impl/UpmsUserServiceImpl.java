package com.bobo.upms.rpc.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.upms.rpc.api.IUpmsUserService;
import com.bobo.upms.rpc.dao.mapper.UpmsUserMapper;
import com.bobo.upms.rpc.pojo.UpmsUser;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public class UpmsUserServiceImpl extends ServiceImpl<UpmsUserMapper, UpmsUser> implements IUpmsUserService {

    private static final Logger _log = LoggerFactory.getLogger(UpmsUserServiceImpl.class);

    @Autowired
    private UpmsUserMapper upmsUserMapper;

    @Transactional
    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        EntityWrapper<UpmsUser> ew = new EntityWrapper<>();
        ew.eq("username",upmsUser.getUsername());

        int count = upmsUserMapper.selectCount(ew);
        if(count > 0){
            return null;
        }
        upmsUserMapper.insert(upmsUser);
        return upmsUser;
    }
}
