package com.bins.binbatis.binding;

import com.bins.binbatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author leo-bin
 * @date 2020/5/6 23:20
 * @apiNote mapper接口的代理类
 */
public class MapperProxy implements InvocationHandler {


    private SqlSession sqlSession;


    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    /**
     * 拦截实际调用的方法并作出转发
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getDeclaringClass().getName() + "." + method.getName());
        //根据返回类型判断是查询单个结果还是集合结果
        if (Collection.class.isAssignableFrom(method.getReturnType())) {
            return sqlSession.selectList(method
                    .getDeclaringClass().getName()
                    + "."
                    + method.getName(), args == null ? null : args[0]);
        } else {
            return sqlSession.selectOne(method
                    .getDeclaringClass().getName()
                    + "."
                    + method.getName(), args == null ? null : args[0]);
        }
    }
}
