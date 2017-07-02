package com.song.domain;

import java.io.Serializable;

public class SysRole implements Serializable {
    /**
     * 对应数据库 sys_role.id
     * 
     */
    private Long id;

    /**
     * 对应数据库 sys_role.role
     * 
     */
    private String role;

    /**
     * 对应数据库 sys_role.description
     * 
     */
    private String description;

    /**
     * 对应数据库 sys_role.resource_ids
     * 
     */
    private String resourceIds;

    /**
     * 对应数据库 sys_role.available
     * 
     */
    private Boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}