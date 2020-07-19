package com.bins.binbatis.executor;

import com.bins.binbatis.config.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @author leo-bin
 * @date 2020/5/6 21:50
 * @apiNote sql执行器（其实就是封装了JDBC对数据库的操作的方法）
 */
public interface Executor {
    /**
     * 查询接口
     *
     * @param mappedStatement mappers.xml中的sql字段<select>***</select>
     * @param parameter       查询参数
     * @param <E>             返回结果
     */
    <E> List<E> query(MappedStatement mappedStatement, Object parameter);

    /**
     * 修改
     *
     * @param mappedStatement 修改sql
     * @param parameter       修改参数
     * @return 修改影响结果
     * @throws SQLException
     */
    int update(MappedStatement mappedStatement, Object parameter) throws SQLException;
}
