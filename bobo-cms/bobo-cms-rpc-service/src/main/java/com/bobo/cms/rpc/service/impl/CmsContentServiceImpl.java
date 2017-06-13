package com.bobo.cms.rpc.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.cms.rpc.api.ICmsContentService;
import com.bobo.cms.rpc.dao.mapper.CmsContentMapper;
import com.bobo.cms.rpc.pojo.CmsContent;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 内容表，用于存放比如文章、帖子、商品、问答等用户自定义模型内容。也用来存放比如菜单、购物车、消费记录等系统模型。 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@Service
public class CmsContentServiceImpl extends ServiceImpl<CmsContentMapper, CmsContent> implements ICmsContentService {
	
}
