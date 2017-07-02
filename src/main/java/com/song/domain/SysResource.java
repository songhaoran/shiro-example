package com.song.domain;

import java.io.Serializable;

public class SysResource implements Serializable {
    /**
     * 对应数据库 sys_resource.id
     * 
     */
    private Long id;

    /**
     * 对应数据库 sys_resource.name
     * 
     */
    private String name;

    /**
     * 对应数据库 sys_resource.type
     * 
     */
    private String type;

    /**
     * 对应数据库 sys_resource.url
     * 
     */
    private String url;

    /**
     * 对应数据库 sys_resource.parent_id
     * 
     */
    private Long parentId;

    /**
     * 对应数据库 sys_resource.parent_ids
     * 
     */
    private String parentIds;

    /**
     * 对应数据库 sys_resource.permission
     * 
     */
    private String permission;

    /**
     * 对应数据库 sys_resource.available
     * 
     */
    private Boolean available;


    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }

    public boolean isRootNode() {
        return parentId == 0;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}