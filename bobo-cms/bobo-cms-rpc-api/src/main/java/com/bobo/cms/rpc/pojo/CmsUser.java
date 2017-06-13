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
 * 用户信息表，保存用户信息。
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@TableName("cms_user")
public class CmsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 登录名
     */
	private String username;
    /**
     * 昵称
     */
	private String nickname;
    /**
     * 实名
     */
	private String realname;
    /**
     * 密码
     */
	private String password;
    /**
     * 盐
     */
	private String salt;
    /**
     * 邮件
     */
	private String email;
    /**
     * 邮箱状态（是否认证等）
     */
	@TableField("email_status")
	private String emailStatus;
    /**
     * 手机电话
     */
	private String mobile;
    /**
     * 手机状态（是否认证等）
     */
	@TableField("mobile_status")
	private String mobileStatus;
    /**
     * 固定电话
     */
	private String telephone;
    /**
     * 金额（余额）
     */
	private BigDecimal amount;
    /**
     * 性别
     */
	private String gender;
    /**
     * 权限
     */
	private String role;
    /**
     * 签名
     */
	private String signature;
    /**
     * 内容数量
     */
	@TableField("content_count")
	private Integer contentCount;
    /**
     * 评论数量
     */
	@TableField("comment_count")
	private Integer commentCount;
    /**
     * QQ号码
     */
	private String qq;
    /**
     * 微信号
     */
	private String wechat;
    /**
     * 微博
     */
	private String weibo;
	private String facebook;
	private String linkedin;
    /**
     * 生日
     */
	private Date birthday;
    /**
     * 公司
     */
	private String company;
    /**
     * 职位、职业
     */
	private String occupation;
    /**
     * 地址
     */
	private String address;
    /**
     * 邮政编码
     */
	private String zipcode;
    /**
     * 个人网址
     */
	private String site;
    /**
     * 毕业学校
     */
	private String graduateschool;
    /**
     * 学历
     */
	private String education;
    /**
     * 头像
     */
	private String avatar;
    /**
     * 证件类型：身份证 护照 军官证等
     */
	private String idcardtype;
    /**
     * 证件号码
     */
	private String idcard;
    /**
     * 状态
     */
	private String status;
    /**
     * 创建日期
     */
	private Date created;
    /**
     * 用户来源（可能来之oauth第三方）
     */
	@TableField("create_source")
	private String createSource;
    /**
     * 最后的登录时间
     */
	private Date logged;
    /**
     * 激活时间
     */
	private Date activated;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobileStatus() {
		return mobileStatus;
	}

	public void setMobileStatus(String mobileStatus) {
		this.mobileStatus = mobileStatus;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getContentCount() {
		return contentCount;
	}

	public void setContentCount(Integer contentCount) {
		this.contentCount = contentCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getGraduateschool() {
		return graduateschool;
	}

	public void setGraduateschool(String graduateschool) {
		this.graduateschool = graduateschool;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getIdcardtype() {
		return idcardtype;
	}

	public void setIdcardtype(String idcardtype) {
		this.idcardtype = idcardtype;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreateSource() {
		return createSource;
	}

	public void setCreateSource(String createSource) {
		this.createSource = createSource;
	}

	public Date getLogged() {
		return logged;
	}

	public void setLogged(Date logged) {
		this.logged = logged;
	}

	public Date getActivated() {
		return activated;
	}

	public void setActivated(Date activated) {
		this.activated = activated;
	}

}
