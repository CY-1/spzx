package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AddUserRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.system.SysRoleDto;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleMapper {
    public List<SysRole> findByPage(SysRoleDto sysRoleDto) ;

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteSysRole(Long id);

    List<SysRole> getAll();

    List<SysRole> finAllRoles(Long userId);

    void addUserRole(AddUserRoleDto data);

    void deleteUserRole(Long userId);
}
