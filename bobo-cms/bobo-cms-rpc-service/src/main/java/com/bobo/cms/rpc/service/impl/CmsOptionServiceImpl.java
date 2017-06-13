package com.bobo.cms.rpc.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.cms.rpc.api.ICmsOptionService;
import com.bobo.cms.rpc.dao.mapper.CmsOptionMapper;
import com.bobo.cms.rpc.pojo.CmsOption;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置信息表，用来保存网站的所有配置信息。 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@Service
public class CmsOptionServiceImpl extends ServiceImpl<CmsOptionMapper, CmsOption> implements ICmsOptionService {
	
}
