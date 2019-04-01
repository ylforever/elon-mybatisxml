package com.elon.rest;

import com.elon.mapper.IUserMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Resource
    private IUserMapper userMapper;

    @GetMapping("/alluser")
    public int queryUserInfo(){
        return userMapper.queryUserInfo().size();
    }
}
