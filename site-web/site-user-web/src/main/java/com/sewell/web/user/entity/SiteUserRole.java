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
@TableName("site_user_role")
public class SiteUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 主键ID
     */
        @TableId(value = "user_role_id", type = IdType.AUTO)
      private Integer userRoleId;

      /**
     * 用户id
     */
      private Integer userId;

      /**
     * 角色id
     */
      private Integer roleId;

    private LocalDateTime updateTime;

    private String updateUser;

    private LocalDateTime createTime;

    private String createUser;

    private String delFlag;

    
    public Integer getUserRoleId() {
        return userRoleId;
    }

      public void setUserRoleId(Integer userRoleId) {
          this.userRoleId = userRoleId;
      }
    
    public Integer getUserId() {
        return userId;
    }

      public void setUserId(Integer userId) {
          this.userId = userId;
      }
    
    public Integer getRoleId() {
        return roleId;
    }

      public void setRoleId(Integer roleId) {
          this.roleId = roleId;
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
        return "SiteUserRole{" +
              "userRoleId=" + userRoleId +
                  ", userId=" + userId +
                  ", roleId=" + roleId +
                  ", updateTime=" + updateTime +
                  ", updateUser=" + updateUser +
                  ", createTime=" + createTime +
                  ", createUser=" + createUser +
                  ", delFlag=" + delFlag +
              "}";
    }
}
