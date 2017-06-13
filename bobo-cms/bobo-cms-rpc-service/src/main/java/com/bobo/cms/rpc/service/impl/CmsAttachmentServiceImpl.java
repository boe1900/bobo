package com.bobo.cms.rpc.service.impl;

import com.bobo.cms.rpc.api.ICmsAttachmentService;
import com.bobo.cms.rpc.pojo.CmsAttachment;
import com.bobo.cms.rpc.dao.mapper.CmsAttachmentMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 附件表，用于保存用户上传的附件内容。 服务实现类
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@Service
public class CmsAttachmentServiceImpl extends ServiceImpl<CmsAttachmentMapper, CmsAttachment> implements ICmsAttachmentService {
	
}
