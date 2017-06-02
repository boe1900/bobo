package com.bobo.upms.rpc.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.upms.rpc.api.IUpmsUserOrganizationService;
import com.bobo.upms.rpc.dao.mapper.UpmsUserOrganizationMapper;
import com.bobo.upms.rpc.pojo.UpmsOrganization;
import com.bobo.upms.rpc.pojo.UpmsUserOrganization;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户组织关联表 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public class UpmsUserOrganizationServiceImpl extends ServiceImpl<UpmsUserOrganizationMapper, UpmsUserOrganization> implements IUpmsUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserOrganizationServiceImpl.class);



    @Autowired
    UpmsUserOrganizationMapper upmsUserOrganizationMapper;


    @Transactional
    @Override
    public int organization(String[] organizationIds, int id) {
        int result = 0;
        //删除旧记录
        EntityWrapper<UpmsUserOrganization> ew = new EntityWrapper<>();
        ew.eq("user_id",id);
        upmsUserOrganizationMapper.delete(ew);

        if(null != organizationIds){
            for(String organizationId:organizationIds){
                if(StringUtils.isBlank(organizationId)){
                    continue;
                }
                UpmsUserOrganization upmsUserOrganization = new UpmsUserOrganization();
                upmsUserOrganization.setOrganizationId(NumberUtils.toInt(organizationId));
                upmsUserOrganization.setUserId(id);
                result = upmsUserOrganizationMapper.insert(upmsUserOrganization);
            }
        }

        return result;
    }
}
