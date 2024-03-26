package com.sewell.web.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author sewell
 * @since 2024-03-25
 */
@TableName("site_user")
public class SiteUser implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 用户编号
     */
        @TableId(value = "user_id", type = IdType.AUTO)
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
      private LocalDateTime updateTime;

      /**
     * 更新用户
     */
      private String updateUser;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;

      /**
     * 创建用户
     */
      private String createUser;

      /**
     * 数据状态：1已删除0正常
     */
      private String delFlag;

    
    public Integer getUserId() {
        return userId;
    }

      public void setUserId(Integer userId) {
          this.userId = userId;
      }
    
    public String getUserName() {
        return userName;
    }

      public void setUserName(String userName) {
          this.userName = userName;
      }
    
    public String getUserPwd() {
        return userPwd;
    }

      public void setUserPwd(String userPwd) {
          this.userPwd = userPwd;
      }
    
    public String getUserNickName() {
        return userNickName;
    }

      public void setUserNickName(String userNickName) {
          this.userNickName = userNickName;
      }
    
    public String getUserStatus() {
        return userStatus;
    }

      public void setUserStatus(String userStatus) {
          this.userStatus = userStatus;
      }
    
    public String getUserSex() {
        return userSex;
    }

      public void setUserSex(String userSex) {
          this.userSex = userSex;
      }
    
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

      public void setUpdateTime(LocalDateTime updateTime) {
          this.updateTime = updateTime;
      }
    
    public String getUpdateUser() {
        return updateUser;
    }

      public void setUpdateUser(String updateUser) {
          this.updateUser = updateUser;
      }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }

      public void setCreateTime(LocalDateTime createTime) {
          this.createTime = createTime;
      }
    
    public String getCreateUser() {
        return createUser;
    }

      public void setCreateUser(String createUser) {
          this.createUser = createUser;
      }
    
    public String getDelFlag() {
        return delFlag;
    }

      public void setDelFlag(String delFlag) {
          this.delFlag = delFlag;
      }

    @Override
    public String toString() {
        return "SiteUser{" +
              "userId=" + userId +
                  ", userName=" + userName +
                  ", userPwd=" + userPwd +
                  ", userNickName=" + userNickName +
                  ", userStatus=" + userStatus +
                  ", userSex=" + userSex +
                  ", updateTime=" + updateTime +
                  ", updateUser=" + updateUser +
                  ", createTime=" + createTime +
                  ", createUser=" + createUser +
                  ", delFlag=" + delFlag +
              "}";
    }
}
