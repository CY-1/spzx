package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.entity.system.SysUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserMapper sysUserMapper;
    @Override
    public void login() {

        SysUser sysUser = sysUserMapper.selectByUserName("admin");



    }
}
