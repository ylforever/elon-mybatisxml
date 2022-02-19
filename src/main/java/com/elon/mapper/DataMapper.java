package com.elon.mapper;

import com.elon.model.InsertValueModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 动态数据查询接口
 *
 * @author elon
 * @since 2022-02-19
 */
@Mapper
public interface DataMapper {
    /**
     * 批量插入数据
     *
     * @param fieldNameList 数据库字段名称列表
     * @param tableName     数据库表名
     * @param bulkDataList  数据库多行数据
     * @author elon
     */
    void batchInsertData(@Param("fieldNameList") List<String> fieldNameList,
                     @Param("tableName") String tableName,
                     @Param("list") List<InsertValueModel> bulkDataList);
}