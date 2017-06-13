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
 * 内容表，用于存放比如文章、帖子、商品、问答等用户自定义模型内容。也用来存放比如菜单、购物车、消费记录等系统模型。
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@TableName("cms_content")
public class CmsContent implements Serializable {

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
     * 内容
     */
	private String text;
    /**
     * 摘要
     */
	private String summary;
    /**
     * 连接到(常用于谋文章只是一个连接)
     */
	@TableField("link_to")
	private String linkTo;
    /**
     * 是否启用markdown
     */
	@TableField("markdown_enable")
	private Integer markdownEnable;
    /**
     * 缩略图
     */
	private String thumbnail;
    /**
     * 模型
     */
	private String module;
    /**
     * 样式
     */
	private String style;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 匿名稿的用户
     */
	private String author;
    /**
     * 匿名稿的邮箱
     */
	@TableField("user_email")
	private String userEmail;
    /**
     * IP地址
     */
	@TableField("user_ip")
	private String userIp;
    /**
     * 发布浏览agent
     */
	@TableField("user_agent")
	private String userAgent;
    /**
     * 父级内容ID
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 关联的对象ID
     */
	@TableField("object_id")
	private Long objectId;
    /**
     * 排序编号
     */
	@TableField("order_number")
	private Integer orderNumber;
    /**
     * 状态
     */
	private String status;
    /**
     * 支持人数
     */
	@TableField("vote_up")
	private Integer voteUp;
    /**
     * 反对人数
     */
	@TableField("vote_down")
	private Integer voteDown;
    /**
     * 评分分数
     */
	private Integer rate;
    /**
     * 评分次数
     */
	@TableField("rate_count")
	private Integer rateCount;
    /**
     * 价格
     */
	private BigDecimal price;
    /**
     * 评论状态
     */
	@TableField("comment_status")
	private String commentStatus;
    /**
     * 评论总数
     */
	@TableField("comment_count")
	private Integer commentCount;
    /**
     * 最后评论时间
     */
	@TableField("comment_time")
	private Date commentTime;
    /**
     * 访问量
     */
	@TableField("view_count")
	private Integer viewCount;
    /**
     * 创建日期
     */
	private Date created;
    /**
     * 最后更新日期
     */
	private Date modified;
    /**
     * slug
     */
	private String slug;
    /**
     * 标识
     */
	private String flag;
    /**
     * 经度
     */
	private BigDecimal lng;
    /**
     * 纬度
     */
	private BigDecimal lat;
    /**
     * SEO关键字
     */
	@TableField("meta_keywords")
	private String metaKeywords;
    /**
     * SEO描述信息
     */
	@TableField("meta_description")
	private String metaDescription;
    /**
     * 备注信息
     */
	private String remarks;


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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLinkTo() {
		return linkTo;
	}

	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}

	public Integer getMarkdownEnable() {
		return markdownEnable;
	}

	public void setMarkdownEnable(Integer markdownEnable) {
		this.markdownEnable = markdownEnable;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
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

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getVoteUp() {
		return voteUp;
	}

	public void setVoteUp(Integer voteUp) {
		this.voteUp = voteUp;
	}

	public Integer getVoteDown() {
		return voteDown;
	}

	public void setVoteDown(Integer voteDown) {
		this.voteDown = voteDown;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getRateCount() {
		return rateCount;
	}

	public void setRateCount(Integer rateCount) {
		this.rateCount = rateCount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
