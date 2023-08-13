package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system/index")

public class IndexController {
    @Resource
    SysUserService sysUserService;
    @PostMapping("login")
    public Result login(){
        sysUserService.login();
        return Result.build(ResultCodeEnum.SUCCESS , null);
    }
}
