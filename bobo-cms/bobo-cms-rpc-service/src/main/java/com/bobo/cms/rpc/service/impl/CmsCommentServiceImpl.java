package com.bobo.cms.rpc.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bobo.cms.rpc.api.ICmsCommentService;
import com.bobo.cms.rpc.dao.mapper.CmsCommentMapper;
import com.bobo.cms.rpc.pojo.CmsComment;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表，用于保存content内容的回复、分享、推荐等信息。 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@Service
public class CmsCommentServiceImpl extends ServiceImpl<CmsCommentMapper, CmsComment> implements ICmsCommentService {
	
}
