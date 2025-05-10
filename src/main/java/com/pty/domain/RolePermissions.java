package com.pty.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName role_permissions
 */
@TableName(value ="role_permissions")
@Data
public class RolePermissions implements Serializable {
    /**
     * 角色对应的id
     */
    @TableId
    private Integer roleId;

    /**
     * 对应的权限
     */

    private Integer permissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}