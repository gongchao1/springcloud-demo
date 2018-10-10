package com.sckj.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangheduo
 * @since 2018-09-05
 */
@TableName("sys_role_authority")
public class SysRoleAuthority extends Model<SysRoleAuthority> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色id
     */
    @TableField("sys_roleid")
    private Long sysRoleid;
    /**
     * 权限id
     */
    @TableField("authority_id")
    private Long authorityId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysRoleid() {
        return sysRoleid;
    }

    public void setSysRoleid(Long sysRoleid) {
        this.sysRoleid = sysRoleid;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SysRoleAuthority{" +
        "id=" + id +
        ", sysRoleid=" + sysRoleid +
        ", authorityId=" + authorityId +
        "}";
    }
}
