package com.song.shiro.taglib;


import com.song.domain.SysOrganization;
import com.song.domain.SysResource;
import com.song.domain.SysRole;
import com.song.service.OrganizationService;
import com.song.service.ResourceService;
import com.song.service.RoleService;
import com.song.utils.CommonUtils;
import com.song.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

import static com.song.utils.CommonUtils.parseString2LongCollection;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class Functions {
    private static Logger log = LogManager.getLogger(Functions.class);

    public static boolean in(String contain, Object element) throws Exception {
        if(StringUtils.isBlank(contain)) {
            return false;
        }
        return parseString2LongCollection(contain,",").contains(Long.parseLong(element+""));
    }

    public static String organizationName(Long organizationId) {
        SysOrganization organization = getOrganizationService().findOne(organizationId);
        if(organization == null) {
            return "";
        }
        return organization.getName();
    }

    public static String organizationNames(Collection<Long> organizationIds) {
        if(CollectionUtils.isEmpty(organizationIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long organizationId : organizationIds) {
            SysOrganization organization = getOrganizationService().findOne(organizationId);
            if(organization == null) {
                return "";
            }
            s.append(organization.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
    public static String roleName(Long roleId) {
        SysRole role = getRoleService().findOne(roleId);
        if(role == null) {
            return "";
        }
        return role.getDescription();
    }

    public static String roleNames(String roleIds){
        try {
            if(StringUtils.isBlank(roleIds)) {
                return "";
            }

            StringBuilder s = new StringBuilder();
            for(Long roleId : CommonUtils.parseString2LongCollection(roleIds,",")) {
                SysRole role = getRoleService().findOne(roleId);
                if(role == null) {
                    return "";
                }
                s.append(role.getDescription());
                s.append(",");
            }

            if(s.length() > 0) {
                s.deleteCharAt(s.length() - 1);
            }

            return s.toString();
        } catch (Exception e) {
            log.error("Function:",e);
        }
        return "";
    }
    public static String resourceName(Long resourceId) {
        SysResource resource = getResourceService().findOne(resourceId);
        if(resource == null) {
            return "";
        }
        return resource.getName();
    }
    public static String resourceNames(String resourceIds) throws Exception{
        if(StringUtils.isBlank(resourceIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long resourceId : parseString2LongCollection(resourceIds,",")) {
            SysResource resource = getResourceService().findOne(resourceId);
            if(resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }

    private static OrganizationService organizationService;
    private static RoleService roleService;
    private static ResourceService resourceService;

    public static OrganizationService getOrganizationService() {
        if(organizationService == null) {
            organizationService = SpringUtils.getBean(OrganizationService.class);
        }
        return organizationService;
    }

    public static RoleService getRoleService() {
        if(roleService == null) {
            roleService = SpringUtils.getBean(RoleService.class);
        }
        return roleService;
    }

    public static ResourceService getResourceService() {
        if(resourceService == null) {
            resourceService = SpringUtils.getBean(ResourceService.class);
        }
        return resourceService;
    }
}

