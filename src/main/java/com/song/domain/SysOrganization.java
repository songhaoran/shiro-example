package com.song.domain;

import java.io.Serializable;

public class SysOrganization implements Serializable {
    /**
     * 对应数据库 sys_organization.id
     * 
     */
    private Long id;

    /**
     * 对应数据库 sys_organization.name
     * 
     */
    private String name;

    /**
     * 对应数据库 sys_organization.parent_id
     * 
     */
    private Long parentId;

    /**
     * 对应数据库 sys_organization.parent_ids
     * 
     */
    private String parentIds;

    /**
     * 对应数据库 sys_organization.available
     * 
     */
    private Boolean available;
    public boolean isRootNode() {
        return parentId == 0;
    }

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}