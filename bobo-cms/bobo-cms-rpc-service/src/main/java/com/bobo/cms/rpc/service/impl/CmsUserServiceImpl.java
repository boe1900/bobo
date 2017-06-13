package com.bobo.cms.rpc.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.cms.rpc.api.ICmsUserService;
import com.bobo.cms.rpc.dao.mapper.CmsUserMapper;
import com.bobo.cms.rpc.pojo.CmsUser;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表，保存用户信息。 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@Service
public class CmsUserServiceImpl extends ServiceImpl<CmsUserMapper, CmsUser> implements ICmsUserService {
	
}
