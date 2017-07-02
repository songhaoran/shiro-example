package com.song.service.impl;


import com.song.dao.SysRoleMapper;
import com.song.domain.SysRole;
import com.song.service.ResourceService;
import com.song.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service

public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    public SysRole createRole(SysRole role) {
        roleMapper.insertSelective(role);
        return role;
    }

    public SysRole updateRole(SysRole role) {
        roleMapper.updateByPrimaryKeySelective(role);
        return role;
    }

    public void deleteRole(Long roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    @Cacheable(value = {"redisCache","customObjectRedisCache"})
    public SysRole findOne(final Long roleId) {
        System.out.println("******************" + roleId);
        redisTemplate.boundHashOps(roleId+"").put("test","vale");
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<SysRole> findAll() {
        return roleMapper.selectAll();
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roleNames = new HashSet<String>();
      /*  for (Long roleId : roleIds) {
            SysRole role = findOne(roleId);
            if (role != null) {
                roles.add(role.getRole());
            }
        }*/
        List<SysRole> roles = roleMapper.selectRoleNameByIds(roleIds);
        if (roles != null && roles.size() > 0) {
            for (SysRole role : roles) {
                String roleName = role.getRole();
                if (StringUtils.isNotBlank(roleName)) {
                    roleNames.add(roleName);
                }
            }
        }
        return roleNames;
    }

    @Override
    @Cacheable()
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIdsSet = new HashSet<Long>();
       /* for (Long roleId : roleIds) {
            SysRole SysRole = findOne(roleId);
            if (SysRole != null) {
                resourceIds.addAll(SysRole.getResourceIds());
            }
        }*/
        List<SysRole> roles = roleMapper.selectRoleNameByIds(roleIds);
        if (roles != null && roles.size() > 0) {
            for (SysRole role : roles) {
                String resourceIds = role.getResourceIds();
                if (StringUtils.isNotBlank(resourceIds)) {
                    String[] arr = resourceIds.split(",");
                    Long[] ids = new Long[arr.length];
                    for (int i = 0; i < arr.length; i++) {
                        ids[i] = Long.parseLong(arr[i]);
                    }
                    resourceIdsSet.addAll(Arrays.asList(ids));
                }
            }
        }
        return resourceService.findPermissions(resourceIdsSet);
    }
}
