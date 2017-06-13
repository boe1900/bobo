package com.bobo.cms.rpc.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.cms.rpc.api.ICmsMappingService;
import com.bobo.cms.rpc.dao.mapper.CmsMappingMapper;
import com.bobo.cms.rpc.pojo.CmsMapping;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 内容和分类的多对多映射关系。 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@Service
public class CmsMappingServiceImpl extends ServiceImpl<CmsMappingMapper, CmsMapping> implements ICmsMappingService {
	
}
