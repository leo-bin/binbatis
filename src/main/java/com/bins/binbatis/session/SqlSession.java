package com.bins.binbatis.session;

import java.util.List;

/**
 * @author leo-bin
 * @date 2020/5/6 21:47
 * @apiNote
 */
public interface SqlSession {

    //////////////////////查询start//////////////////////////////

    /**
     * 根据传入的条件查询单一结果
     *
     * @param statement 方法对应 sql 语句，namespace+id
     * @param parameter 要传入 sql 语句中的查询参数
     * @param <T>       返回指定的结果对象
     * @return 结果集
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * @return 返回结果集合
     */
    <T> List<T> selectList(String statement, Object parameter);


    //////////////////////查询end//////////////////////////////


    /////////////////////插入start//////////////////////////////

    /**
     * 插入
     *
     * @param statement 插入sql
     * @param parameter sql参数
     * @return 插入的行数
     */
    int insert(String statement, Object parameter);


    /////////////////////插入end//////////////////////////////


    /////////////////////修改//////////////////////////////

    /**
     * 修改
     *
     * @param statement 修改sql
     * @param parameter 参数
     * @return 修改时影响的行数
     */
    int update(String statement, Object parameter);


    /////////////////////删除start//////////////////////////////

    /**
     * 删除
     *
     * @param statement 删除sql
     * @param parameter 参数
     * @return 删除之后影响的行数
     */
    int delete(String statement, Object parameter);


    /////////////////////删除end//////////////////////////////

    /**
     * 得到一个mapper接口的实现类
     */
    <T> T getMapper(Class<T> type);
}
