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
 * 评论表，用于保存content内容的回复、分享、推荐等信息。
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@TableName("cms_comment")
public class CmsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 回复的评论ID
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 评论的内容ID
     */
	@TableField("content_id")
	private Long contentId;
    /**
     * 评论的内容模型
     */
	@TableField("content_module")
	private String contentModule;
    /**
     * 评论的回复数量
     */
	@TableField("comment_count")
	private Integer commentCount;
    /**
     * 排序编号，常用语置顶等
     */
	@TableField("order_number")
	private Integer orderNumber;
    /**
     * 评论的用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 评论的IP地址
     */
	private String ip;
    /**
     * 评论的作者
     */
	private String author;
    /**
     * 评论的类型，默认是comment
     */
	private String type;
    /**
     * 评论的内容
     */
	private String text;
    /**
     * 提交评论的浏览器信息
     */
	private String agent;
    /**
     * 评论的时间
     */
	private Date created;
    /**
     * 评论的slug
     */
	private String slug;
    /**
     * 评论用户的email
     */
	private String email;
    /**
     * 评论的状态
     */
	private String status;
    /**
     * “顶”的数量
     */
	@TableField("vote_up")
	private Integer voteUp;
    /**
     * “踩”的数量
     */
	@TableField("vote_down")
	private Integer voteDown;
    /**
     * 标识
     */
	private String flag;
    /**
     * 纬度
     */
	private BigDecimal lat;
    /**
     * 经度
     */
	private BigDecimal lng;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getContentModule() {
		return contentModule;
	}

	public void setContentModule(String contentModule) {
		this.contentModule = contentModule;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
