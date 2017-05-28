package com.bobo.upms.rpc.pojo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
@TableName("upms_role_permission")
public class UpmsRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="role_permission_id", type= IdType.AUTO)
	private Integer rolePermissionId;
    /**
     * 角色编号
     */
	@TableField("role_id")
	private Integer roleId;
    /**
     * 权限编号
     */
	@TableField("permission_id")
	private Integer permissionId;


	public Integer getRolePermissionId() {
		return rolePermissionId;
	}

	public void setRolePermissionId(Integer rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

}
