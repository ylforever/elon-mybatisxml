package com.elon.rest;

import com.alibaba.fastjson.JSONObject;
import com.elon.mapper.IUserMapper;
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
    public String queryUserInfo(){
        List<Map<String, String>> resultMap = userMapper.queryUserInfo();
        return JSONObject.toJSONString(resultMap);
    }
}
