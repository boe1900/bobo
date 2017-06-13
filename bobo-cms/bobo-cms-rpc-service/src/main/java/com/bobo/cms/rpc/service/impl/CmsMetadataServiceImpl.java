package com.bobo.cms.rpc.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.cms.rpc.api.ICmsMetadataService;
import com.bobo.cms.rpc.dao.mapper.CmsMetadataMapper;
import com.bobo.cms.rpc.pojo.CmsMetadata;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 元数据表，用来对其他表的字段扩充。 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@Service
public class CmsMetadataServiceImpl extends ServiceImpl<CmsMetadataMapper, CmsMetadata> implements ICmsMetadataService {
	
}
