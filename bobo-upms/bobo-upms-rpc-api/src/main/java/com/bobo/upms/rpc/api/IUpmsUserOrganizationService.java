package com.bobo.upms.rpc.api;

import com.baomidou.mybatisplus.service.IService;
import com.bobo.upms.rpc.pojo.UpmsUserOrganization;

/**
 * <p>
 * 用户组织关联表 服务类
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
public interface IUpmsUserOrganizationService extends IService<UpmsUserOrganization> {
    /**
     * 用户组织
     * @param organizationIds 组织ids
     * @param id 用户id
     * @return
     */
    int organization(String[] organizationIds, int id);
}
