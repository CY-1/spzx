package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    public abstract List<SysMenu> selectAll();

    void save(SysMenu sysMenu);

    void removeById(Long id);

    int countByParentId(Long id);

    SysMenu getById(String id);

    void updateNode(SysMenu sysMenu);

    List<SysMenu> selectListByUserId(Long id);
}