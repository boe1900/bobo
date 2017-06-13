package com.bobo.cms.rpc.pojo;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 分类表。标签、专题、类别等都属于taxonomy。
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@TableName("cms_taxonomy")
public class CmsTaxonomy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 标题
     */
	private String title;
    /**
     * 内容描述
     */
	private String text;
    /**
     * slug
     */
	private String slug;
    /**
     * 类型
     */
	private String type;
    /**
     * 图标
     */
	private String icon;
    /**
     * 对于的内容模型
     */
	@TableField("content_module")
	private String contentModule;
    /**
     * 该分类的内容数量
     */
	@TableField("content_count")
	private Integer contentCount;
    /**
     * 排序编码
     */
	@TableField("order_number")
	private Integer orderNumber;
    /**
     * 父级分类的ID
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 关联的对象ID
     */
	@TableField("object_id")
	private Long objectId;
    /**
     * 标识
     */
	private String flag;
    /**
     * 经度
     */
	private BigDecimal lat;
    /**
     * 纬度
     */
	private BigDecimal lng;
    /**
     * SEO关键字
     */
	@TableField("meta_keywords")
	private String metaKeywords;
    /**
     * SEO描述内容
     */
	@TableField("meta_description")
	private String metaDescription;
    /**
     * 创建日期
     */
	private Date created;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getContentModule() {
		return contentModule;
	}

	public void setContentModule(String contentModule) {
		this.contentModule = contentModule;
	}

	public Integer getContentCount() {
		return contentCount;
	}

	public void setContentCount(Integer contentCount) {
		this.contentCount = contentCount;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
