package com.atguigu.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper ;

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 检查验证码
        String captcha =loginDto.getCaptcha();
        String codeKey= loginDto.getCodeKey();
        String redisCode = redisTemplate.opsForValue().get("user:login:validatecode:" + codeKey);

        if(StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode , captcha)) {
            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR) ;
        }

        // 根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());
        if(sysUser == null) {
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 验证密码是否正确
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if(!md5InputPassword.equals(sysUser.getPassword())) {
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR) ;
        }

        // 生成令牌，保存数据到Redis中
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login:" + token , JSON.toJSONString(sysUser) , 30 , TimeUnit.MINUTES);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo() ;
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        //验证通过后删除
        redisTemplate.delete("user:login:validatecode:" + codeKey);
        // 返回
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {

            String userJson = redisTemplate.opsForValue().get("user:login:" + token);
            return JSON.parseObject(userJson , SysUser.class) ;
        }
    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token) ;
    }


    //增删查改
    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> users= sysUserMapper.findByPage(sysUserDto);
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(users);
        return sysUserPageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        sysUserMapper.saveSysUser(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteSysUser(Long id) {
        int i = sysUserMapper.deleteSysUser(id);
        if(i==0){
            throw new GuiguException(ResultCodeEnum.DELETE_ERROR);
        }
    }


}