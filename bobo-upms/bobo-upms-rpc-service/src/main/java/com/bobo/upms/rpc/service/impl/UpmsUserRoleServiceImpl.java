package com.bobo.upms.rpc.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.upms.rpc.api.IUpmsUserRoleService;
import com.bobo.upms.rpc.dao.mapper.UpmsUserRoleMapper;
import com.bobo.upms.rpc.pojo.UpmsUserRole;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public class UpmsUserRoleServiceImpl extends ServiceImpl<UpmsUserRoleMapper, UpmsUserRole> implements IUpmsUserRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserRoleServiceImpl.class);

    @Autowired
    private UpmsUserRoleMapper upmsUserRoleMapper;


    @Transactional
    @Override
    public int role(String[] roleIds, int id) {
        int result = 0;
        //删除旧记录
        EntityWrapper<UpmsUserRole> ew = new EntityWrapper<>();
        ew.eq("user_id",id);
        upmsUserRoleMapper.delete(ew);
        //增加新记录
        if(null != roleIds){
            for(String roleId:roleIds){
                if(StringUtils.isBlank(roleId)){
                    continue;
                }
                UpmsUserRole upmsUserRole = new UpmsUserRole();
                upmsUserRole.setUserId(id);
                upmsUserRole.setRoleId(NumberUtils.toInt(roleId));
                result = upmsUserRoleMapper.insert(upmsUserRole);
            }
        }

        return result;
    }
}
