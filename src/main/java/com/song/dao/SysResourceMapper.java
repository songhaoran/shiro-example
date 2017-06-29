package com.song.dao;

import com.song.domain.SysResource;

import java.util.List;

public interface SysResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);

    List<SysResource> selectAll();
}