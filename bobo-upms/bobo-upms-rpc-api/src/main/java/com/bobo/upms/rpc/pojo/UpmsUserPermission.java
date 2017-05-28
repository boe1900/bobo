package com.bobo.upms.rpc.pojo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户权限关联表
 * </p>
 *
 * @author huabo
 * @since 2017-05-26
 */
@TableName("upms_user_permission")
public class UpmsUserPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="user_permission_id", type= IdType.AUTO)
	private Integer userPermissionId;
    /**
     * 用户编号
     */
	@TableField("user_id")
	private Integer userId;
    /**
     * 权限编号
     */
	@TableField("permission_id")
	private Integer permissionId;
    /**
     * 权限类型(-1:减权限,1:增权限)
     */
	private Integer type;


	public Integer getUserPermissionId() {
		return userPermissionId;
	}

	public void setUserPermissionId(Integer userPermissionId) {
		this.userPermissionId = userPermissionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
