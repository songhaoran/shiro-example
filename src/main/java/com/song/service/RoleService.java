package com.song.service;

import com.song.domain.SysRole;

import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface RoleService {


    public SysRole createRole(SysRole role);
    public SysRole updateRole(SysRole role);
    public void deleteRole(Long roleId);

    public SysRole findOne(Long roleId);
    public List<SysRole> findAll();

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Long... roleIds);

    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds
     * @return
     */
    Set<String> findPermissions(Long[] roleIds);
}
