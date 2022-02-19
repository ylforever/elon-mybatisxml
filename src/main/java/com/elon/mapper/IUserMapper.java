package com.elon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IUserMapper {

    /**
     * 查询用户信息
     * @return
     */
    List<Map<String, String>> queryUserInfo();

    void executeUpdateSql(@Param("sql") String sql);
}
