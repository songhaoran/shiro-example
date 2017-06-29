package com.song.service.impl;


import com.song.dao.SysOrganizationMapper;
import com.song.domain.SysOrganization;
import com.song.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private SysOrganizationMapper organizationMapper;

    @Override
    public SysOrganization createOrganization(SysOrganization organization) {
         organizationMapper.insertSelective(organization);
        return organization;
    }

    @Override
    public SysOrganization updateOrganization(SysOrganization organization) {
         organizationMapper.updateByPrimaryKeySelective(organization);
        return organization;
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationMapper.deleteByPrimaryKey(organizationId);
    }

    @Override
    public SysOrganization findOne(Long organizationId) {
        return organizationMapper.selectByPrimaryKey(organizationId);
    }

    @Override
    public List<SysOrganization> findAll() {
        return organizationMapper.selectAll();
    }

   /* @Override
    public List<SysOrganization> findAllWithExclude(SysOrganization excludeOraganization) {
        return organizationMapper.findAllWithExclude(excludeOraganization);
    }

    @Override
    public void move(SysOrganization source, SysOrganization target) {
        organizationMapper.move(source, target);
    }*/
}
