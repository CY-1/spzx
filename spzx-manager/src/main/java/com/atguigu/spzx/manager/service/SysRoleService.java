package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AddUserRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.system.SysRoleDto;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteSysRole(Long id);

    List<SysRole> getAll();

    List<SysRole> findAllRoles(Long userId);

    void addUserRole(AddUserRoleDto data);
}