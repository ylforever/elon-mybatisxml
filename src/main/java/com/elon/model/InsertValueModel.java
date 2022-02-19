package com.elon.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 单条插入数据库的数据
 *
 * @author elon
 * @since 2022-02-19
 */
@Getter
@Setter
public class InsertValueModel {
    /**
     * 用于接收回填的自增ID
     */
    private int id = -1;

    /**
     * 数据列表
     */
    private List<Object> dataList = new ArrayList<>();

    public static InsertValueModel create(Object... values) {
        InsertValueModel valueModel = new InsertValueModel();
        valueModel.dataList = Arrays.asList(values);
        return valueModel;
    }
}
