package com.elon.rest;

import com.alibaba.fastjson.JSONObject;
import com.elon.mapper.DataMapper;
import com.elon.mapper.IUserMapper;
import com.elon.model.InsertValueModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/user")
@Api(tags = "测试接口")
public class UserController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("myDataSource")
    private DataSource dataSource;

    @Resource
    private IUserMapper userMapper;

    @Resource
    private DataMapper dataMapper;

    @GetMapping("/alluser")
    @ApiOperation(value = "获取所有用户")
    public String queryUserInfo() {
        List<Map<String, String>> resultMap = userMapper.queryUserInfo();
        return JSONObject.toJSONString(resultMap);
    }

    @PostMapping("/execute-sql")
    @ApiOperation(value = "执行SQL")
    public void executeSql(@RequestBody String sql) {
        userMapper.executeUpdateSql(sql);
    }

    @PostMapping("/insert-user")
    @ApiOperation(value = "插入用户")
    public void insertUsers() throws SQLException {
        Connection connection = dataSource.getConnection();

        String sql = "INSERT INTO t_user (name, age) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "王五");
        preparedStatement.setInt(2, 35);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @PostMapping("bulk-insert-user-data")
    @ApiOperation(value = "批量插入用户数据")
    public List<Integer> bulkInsertUserData() {
        // 初始化拼接SQL用的表名和字段名
        List<String> fieldNameList = Arrays.asList("name", "age");
        String tableName = "t_user";

        // 初始化批量数据
        List<InsertValueModel> bulkDataList = new ArrayList<>();
        bulkDataList.add(InsertValueModel.create("张三", 12));
        bulkDataList.add(InsertValueModel.create("李四", 15));
        bulkDataList.add(InsertValueModel.create("王五", 19));

        dataMapper.batchInsertData(fieldNameList, tableName, bulkDataList);

        List<Integer> ids = bulkDataList.stream().map(InsertValueModel::getId).collect(Collectors.toList());
        LOGGER.info("打印自增ID:{}", ids);
        return ids;
    }
}
