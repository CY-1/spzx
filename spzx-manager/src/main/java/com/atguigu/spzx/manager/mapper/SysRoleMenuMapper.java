package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {
    List<Long> findSysRoleMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);

    void deleteByRoleId(Long roleId);

    void updateSysRoleMenuIsHalf(Long id);
}
