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
 * 附件表，用于保存用户上传的附件内容。
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@TableName("cms_attachment")
public class CmsAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 标题
     */
	private String title;
    /**
     * 上传附件的用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 附件所属的内容ID
     */
	@TableField("content_id")
	private Long contentId;
    /**
     * 路径
     */
	private String path;
    /**
     * mime
     */
	@TableField("mime_type")
	private String mimeType;
    /**
     * 附件的后缀
     */
	private String suffix;
    /**
     * 类型
     */
	private String type;
    /**
     * 标示
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
     * 排序字段
     */
	@TableField("order_number")
	private Integer orderNumber;
    /**
     * 上传时间
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
