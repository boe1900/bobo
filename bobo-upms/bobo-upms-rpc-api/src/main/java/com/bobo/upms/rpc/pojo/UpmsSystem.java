package com.bobo.upms.rpc.pojo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 系统
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
@TableName("upms_system")
public class UpmsSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="system_id", type= IdType.AUTO)
	private Integer systemId;
    /**
     * 图标
     */
	private String icon;
    /**
     * 背景
     */
	private String banner;
    /**
     * 主题
     */
	private String theme;
    /**
     * 根目录
     */
	private String basepath;
    /**
     * 状态(-1:黑名单,1:正常)
     */
	private Integer status;
    /**
     * 系统名称
     */
	private String name;
    /**
     * 系统标题
     */
	private String title;
    /**
     * 系统描述
     */
	private String description;
    /**
     * 创建时间
     */
	private Long ctime;
    /**
     * 排序
     */
	private Long orders;


	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getBasepath() {
		return basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	public Long getOrders() {
		return orders;
	}

	public void setOrders(Long orders) {
		this.orders = orders;
	}

}
