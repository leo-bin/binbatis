package com.bins.binbatis.binding;

import com.bins.binbatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @author leo-bin
 * @date 2020/5/6 23:42
 * @apiNote mapper代理工厂类
 */
public class MapperProxyFactory {

    /**
     * 根据type生成具体的代理类
     */
    @SuppressWarnings("unchecked")
    public <T> T newInstance(Class<T> type, SqlSession sqlSession) {
        MapperProxy proxy = new MapperProxy(sqlSession);
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, proxy);
    }
}
