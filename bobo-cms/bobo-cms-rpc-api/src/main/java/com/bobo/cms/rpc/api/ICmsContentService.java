package com.bobo.cms.rpc.api;


import com.baomidou.mybatisplus.service.IService;
import com.bobo.cms.rpc.pojo.CmsContent;

/**
 * <p>
 * 内容表，用于存放比如文章、帖子、商品、问答等用户自定义模型内容。也用来存放比如菜单、购物车、消费记录等系统模型。 服务类
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
public interface ICmsContentService extends IService<CmsContent> {
	
}
