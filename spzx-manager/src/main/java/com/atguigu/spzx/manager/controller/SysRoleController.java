package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.AddUserRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.SysRoleDto;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService ;
    @GetMapping("/getAll")
    public Result<List<SysRole>> getALl(){
        return Result.build(sysRoleService.getAll(),ResultCodeEnum.SUCCESS);
    }
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(SysRoleDto sysRoleDto ,
                                                @PathVariable(value = "pageNum") Integer pageNum ,
                                                @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto , pageNum , pageSize) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
    @PostMapping(value = "/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole SysRole) {
        sysRoleService.saveSysRole(SysRole) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @PutMapping(value = "/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @DeleteMapping(value = "/delete/{id}")
    public Result deleteSysRole(@PathVariable Long id) {
        sysRoleService.deleteSysRole(id) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @GetMapping(value = "/findAllRoles/{userId}")
    public Result<List<SysRole>> findAllRoles(@PathVariable(value = "userId") Long userId) {
        List<SysRole> resultMap = sysRoleService.findAllRoles(userId);
        return Result.build(resultMap , ResultCodeEnum.SUCCESS)  ;
    }
    @PostMapping("/addUserRole")
    public Result addUserRole(@RequestBody AddUserRoleDto data){
        sysRoleService.addUserRole(data);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }
}