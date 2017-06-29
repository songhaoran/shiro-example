package com.song.domain;

public class SysUser {
    /**
     * 对应数据库 sys_user.id
     * 
     */
    private Long id;

    /**
     * 对应数据库 sys_user.organization_id
     * 
     */
    private Long organizationId;

    /**
     * 对应数据库 sys_user.username
     * 
     */
    private String username;

    /**
     * 对应数据库 sys_user.password
     * 
     */
    private String password;

    /**
     * 对应数据库 sys_user.salt
     * 
     */
    private String salt;

    /**
     * 对应数据库 sys_user.role_ids
     * 
     */
    private String roleIds;

    /**
     * 对应数据库 sys_user.locked
     * 
     */
    private Boolean locked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getCredentialsSalt() {
        return username+salt;
    }

}