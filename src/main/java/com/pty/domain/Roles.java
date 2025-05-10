package com.pty.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName roles
 */
@TableName(value ="roles")
@Data
public class Roles implements Serializable {
    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 身份：超级管理员、管理员、普通

     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createdAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}