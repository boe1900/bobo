package com.bobo.cms.rpc.pojo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 内容和分类的多对多映射关系。
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@TableName("cms_mapping")
public class CmsMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 内容ID
     */
	@TableField("content_id")
	private Long contentId;
    /**
     * 分类ID
     */
	@TableField("taxonomy_id")
	private Long taxonomyId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public Long getTaxonomyId() {
		return taxonomyId;
	}

	public void setTaxonomyId(Long taxonomyId) {
		this.taxonomyId = taxonomyId;
	}

}
