package com.bins.binbatis.session;

/**
 * @author leo-bin
 * @date 2020/5/6 21:47
 * @apiNote SqlSessionFactory会话工厂顶层接口
 */
public interface SqlSessionFactory {

    /**
     * 创建一个SqlSession
     *
     * @return SqlSession
     */
    SqlSession openSession();
}
