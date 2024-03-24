package com.sewell.common.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
* 
* @TableName site_user
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteUser implements Serializable {

    @Serial
    private static final long serialVersionUID = -6944726363118418986L;
    /**
    * 用户编号
    */
    private Integer userId;
    /**
    * 用户名称
    */
    private String userName;
    /**
    * 密码
    */
    private String userPwd;
    /**
    * 昵称
    */
    private String userNickName;
    /**
    * 状态：A：有效 C：无效
    */
    private String userStatus;
    /**
    * 性别：1:男 2:女
    */
    private String userSex;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 更新用户
    */
    private String updateUser;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 创建用户
    */
    private String createUser;
    /**
    * 数据状态：1已删除0正常
    */
    private String delFlag;


}
