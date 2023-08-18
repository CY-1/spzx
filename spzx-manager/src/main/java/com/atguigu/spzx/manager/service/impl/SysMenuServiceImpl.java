package com.atguigu.spzx.manager.service.impl;

import com.atguigu.model.entity.system.SysUser.AuthContextUtil;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.helper.MenuHelper;
import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper ;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> sysMenuList = sysMenuMapper.selectAll() ;
        if (CollectionUtils.isEmpty(sysMenuList)) return null;
        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList); //构建树形数据
        return treeList;
    }
    @Transactional
    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);
        // 新添加一个菜单，那么此时就需要将该菜单所对应的父级菜单设置为半开
        updateSysRoleMenuIsHalf(sysMenu) ;
    }
    private void updateSysRoleMenuIsHalf(SysMenu sysMenu) {

        // 查询是否存在父节点
        SysMenu parentMenu = sysMenuMapper.getById(sysMenu.getParentId().toString());

        if (parentMenu != null) {

            // 将该id的菜单设置为半开
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());

            // 递归调用
            updateSysRoleMenuIsHalf(parentMenu);

        }
    }
    @Override
    public void removeById(Long id) {
        int count = sysMenuMapper.countByParentId(id);  // 先查询是否存在子菜单，如果存在不允许进行删除
        if (count > 0) {
            throw new GuiguException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.removeById(id);
    }

    @Override
    public SysMenu getNodesById(String id) {
        return sysMenuMapper.getById(id);
    }

    @Override
    public void updateNode(SysMenu sysMenu) {
        sysMenuMapper.updateNode(sysMenu);
    }

    @Override
    public List<SysMenuVo> findUserMenuList() {
        Long id = AuthContextUtil.get().getId();
        List<SysMenu> sysMenuList = sysMenuMapper.selectListByUserId(id) ;

        //构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        return this.buildMenus(sysMenuTreeList);

    }

    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {
        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }

}