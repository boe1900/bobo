package com.bobo.upms.rpc.pojo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
@TableName("upms_permission")
public class UpmsPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="permission_id", type= IdType.AUTO)
	private Integer permissionId;
    /**
     * 所属系统
     */
	@TableField("system_id")
	private Integer systemId;
    /**
     * 所属上级
     */
	private Integer pid;
    /**
     * 名称
     */
	private String name;
    /**
     * 类型(1:目录,2:菜单,3:按钮)
     */
	private Integer type;
    /**
     * 权限值
     */
	@TableField("permission_value")
	private String permissionValue;
    /**
     * 路径
     */
	private String uri;
    /**
     * 图标
     */
	private String icon;
    /**
     * 状态(0:禁止,1:正常)
     */
	private Integer status;
    /**
     * 创建时间
     */
	private Long ctime;
    /**
     * 排序
     */
	private Long orders;


	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
