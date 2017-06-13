package com.bobo.cms.rpc.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.cms.rpc.api.ICmsTaxonomyService;
import com.bobo.cms.rpc.dao.mapper.CmsTaxonomyMapper;
import com.bobo.cms.rpc.pojo.CmsTaxonomy;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类表。标签、专题、类别等都属于taxonomy。 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@Service
public class CmsTaxonomyServiceImpl extends ServiceImpl<CmsTaxonomyMapper, CmsTaxonomy> implements ICmsTaxonomyService {
	
}
