package com.song.dao;

import com.song.domain.SysOrganization;

import java.util.List;

public interface SysOrganizationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysOrganization record);

    int insertSelective(SysOrganization record);

    SysOrganization selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysOrganization record);

    int updateByPrimaryKey(SysOrganization record);

    List<SysOrganization> selectAll();
}