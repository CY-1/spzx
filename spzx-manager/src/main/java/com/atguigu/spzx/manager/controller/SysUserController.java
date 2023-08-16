package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.SysRoleDto;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysUser>> findByPage(SysUserDto sysUserDto ,
                                                @PathVariable(value = "pageNum") Integer pageNum ,
                                                @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto , pageNum , pageSize) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
    @PostMapping(value = "/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser SysUser) {
        sysUserService.saveSysUser(SysUser) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @PutMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @DeleteMapping(value = "/delete/{id}")
    public Result deleteSysUser(@PathVariable Long id) {
        sysUserService.deleteSysUser(id) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
