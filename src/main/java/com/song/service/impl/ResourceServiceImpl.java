package com.song.service.impl;


import com.song.dao.SysResourceMapper;
import com.song.domain.SysResource;
import com.song.service.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private SysResourceMapper resourceMapper;

    @Override
    public SysResource createResource(SysResource resource) {
        resourceMapper.insertSelective(resource);
        return resource;
    }

    @Override
    public SysResource updateResource(SysResource resource) {
         resourceMapper.updateByPrimaryKeySelective(resource);
        return resource;
    }

    @Override
    public void deleteResource(Long resourceId) {
        resourceMapper.deleteByPrimaryKey(resourceId);
    }

    @Override
    public SysResource findOne(Long resourceId) {
        return resourceMapper.selectByPrimaryKey(resourceId);
    }

    @Override
    public List<SysResource> findAll() {
        return resourceMapper.selectAll();
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for (Long resourceId : resourceIds) {
            SysResource resource = findOne(resourceId);
            if (resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<SysResource> findMenus(Set<String> permissions) {
        List<SysResource> allResources = findAll();
        List<SysResource> menus = new ArrayList<SysResource>();
        for (SysResource resource : allResources) {
            if (resource.isRootNode()) {
                continue;
            }
            if (!"menu".equals(resource.getType())) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, SysResource resource) {
        if (StringUtils.isBlank(resource.getPermission())) {
            return true;
        }
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
