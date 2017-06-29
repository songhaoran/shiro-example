package com.song.service;


import com.song.domain.SysOrganization;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface OrganizationService {


    public SysOrganization createOrganization(SysOrganization organization);
    public SysOrganization updateOrganization(SysOrganization organization);
    public void deleteOrganization(Long organizationId);

    SysOrganization findOne(Long organizationId);
    List<SysOrganization> findAll();

    /*Object findAllWithExclude(SysOrganization excludeOraganization);

    void move(SysOrganization source, SysOrganization target);*/
}
