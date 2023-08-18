package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {
    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void removeById(Long id);

    SysMenu getNodesById(String id);

    void updateNode(SysMenu sysMenu);

    List<SysMenuVo> findUserMenuList();
}
