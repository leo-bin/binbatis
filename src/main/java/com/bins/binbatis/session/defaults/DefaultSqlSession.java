package com.bins.binbatis.session.defaults;

import com.bins.binbatis.binding.MapperProxyFactory;
import com.bins.binbatis.config.Configuration;
import com.bins.binbatis.config.MappedStatement;
import com.bins.binbatis.executor.Executor;
import com.bins.binbatis.executor.SimpleExecutor;
import com.bins.binbatis.session.SqlSession;

import java.util.List;

/**
 * @author leo-bin
 * @date 2020/5/6 21:49
 * @apiNote 默认sql会话实现类
 */
public class DefaultSqlSession implements SqlSession {
    
    private final Configuration configuration;

    private Executor executor;


    public DefaultSqlSession(Configuration configuration) {
        super();
        this.configuration = configuration;
        executor = new SimpleExecutor();
    }


    @Override
    public <T> T selectOne(String statement, Object parameter) {
        List<T> list = this.<T>selectList(statement, parameter);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new RuntimeException("too many results!");
        } else {
            return null;
        }
    }


    @Override
    public <T> List<T> selectList(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatements().get(statement);
        return executor.query(mappedStatement, parameter);
    }


    @Override
    public int insert(String statement, Object parameter) {
        return 0;
    }

    @Override
    public int update(String statement, Object parameter) {
        return 0;
    }

    @Override
    public int delete(String statement, Object parameter) {
        return 0;
    }


    @Override
    public <T> T getMapper(Class<T> type) {
        MapperProxyFactory proxyFactory = new MapperProxyFactory();
        return proxyFactory.newInstance(type, this);
    }
}
