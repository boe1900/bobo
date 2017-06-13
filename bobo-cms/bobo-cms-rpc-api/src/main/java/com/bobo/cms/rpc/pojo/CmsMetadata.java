package com.bobo.cms.rpc.pojo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 元数据表，用来对其他表的字段扩充。
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@TableName("cms_metadata")
public class CmsMetadata implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 元数据key
     */
	@TableField("meta_key")
	private String metaKey;
    /**
     * 元数据value
     */
	@TableField("meta_value")
	private String metaValue;
    /**
     * 元数据的对象类型
     */
	@TableField("object_type")
	private String objectType;
    /**
     * 元数据的对象ID
     */
	@TableField("object_id")
	private Long objectId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMetaKey() {
		return metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	public String getMetaValue() {
		return metaValue;
	}

	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

}
