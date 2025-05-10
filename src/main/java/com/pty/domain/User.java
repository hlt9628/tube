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
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户
     */
    private String userName;

    /**
     * 
     */
    private String userAccount;

    /**
     * 
     */
    private String userPassword;

    /**
     * 
     */
    private String userPhone;

    /**
     * 
     */
    private String userEmail;

    /**
     * 
     */
    private String userImg;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer userStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}