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
@TableName("site_role")
public class SiteRole implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 角色编号
     */
        @TableId(value = "role_id", type = IdType.AUTO)
      private Integer roleId;

      /**
     * 角色编码
     */
      private String roleCode;

      /**
     * 角色描述
     */
      private String roleDesc;

      /**
     * 角色状态
     */
      private String roleSts;

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

    
    public Integer getRoleId() {
        return roleId;
    }

      public void setRoleId(Integer roleId) {
          this.roleId = roleId;
      }
    
    public String getRoleCode() {
        return roleCode;
    }

      public void setRoleCode(String roleCode) {
          this.roleCode = roleCode;
      }
    
    public String getRoleDesc() {
        return roleDesc;
    }

      public void setRoleDesc(String roleDesc) {
          this.roleDesc = roleDesc;
      }
    
    public String getRoleSts() {
        return roleSts;
    }

      public void setRoleSts(String roleSts) {
          this.roleSts = roleSts;
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
        return "SiteRole{" +
              "roleId=" + roleId +
                  ", roleCode=" + roleCode +
                  ", roleDesc=" + roleDesc +
                  ", roleSts=" + roleSts +
                  ", updateTime=" + updateTime +
                  ", updateUser=" + updateUser +
                  ", createTime=" + createTime +
                  ", createUser=" + createUser +
                  ", delFlag=" + delFlag +
              "}";
    }
}
