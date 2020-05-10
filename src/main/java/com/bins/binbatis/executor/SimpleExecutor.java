package com.bins.binbatis.executor;

import com.bins.binbatis.config.MappedStatement;
import com.bins.binbatis.util.JdbcUtil;
import com.bins.binbatis.util.ReflectionUtil;
import com.bins.binbatis.util.SqlParserUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author leo-bin
 * @date 2020/5/6 21:51
 * @apiNote 默认执行器实现类（封装对jdbc的方法）
 */
public class SimpleExecutor implements Executor {


    /**
     * #{userId}
     */
    private static final String regex1 = "#\\{([^}])*\\}";


    /**
     * 查询
     *
     * @param mappedStatement mappers.xml中的sql字段<select>***</select>
     * @param parameter       查询参数
     * @param <E>             泛型
     * @return 结果
     */
    @Override
    public <E> List<E> query(MappedStatement mappedStatement, Object parameter) {
        System.out.println(mappedStatement.getSql());

        List<E> results = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //1.拿到数据库连接实例
        Connection connection = JdbcUtil.getConnection();
        //2.sql语句预编译，并且用?占位,然后将具体的参数set进去
        Map<String, String> map = SqlParserUtil.parse(mappedStatement.getSql());
        String sql = map.get("sql");
        try {
            preparedStatement = connection.prepareStatement(sql);
            setParameter(preparedStatement, parameter, map);
            //3.执行查询
            resultSet = preparedStatement.executeQuery();
            //4.处理sql执行结果
            handlerResultSet(resultSet, results, mappedStatement.getResultType());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.最后关闭连接
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }
        return results;
    }


    /**
     * 修改操作
     *
     * @param mappedStatement 修改sql
     * @param parameter       修改参数
     * @return 修改影响结果
     * @throws SQLException sql异常
     */
    @Override
    public int update(MappedStatement mappedStatement, Object parameter) throws SQLException {
        System.out.println(mappedStatement.getSql());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //1.拿到connection实例
        Connection connection = JdbcUtil.getConnection();
        //2.得到预处理之后的sql
        Map<String, String> map = SqlParserUtil.parse(mappedStatement.getSql());
        String sql = map.get("sql");
        try {
            preparedStatement = connection.prepareStatement(sql);
            // TODO: BY leo-bin 2020/5/9
            // TODO-LIST: 完成update方法
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 处理占位符
     *
     * @param preparedStatement 预处理sql语句对象
     * @param parameter         参数
     * @throws SQLException sql异常
     */
    private void setParameter(PreparedStatement preparedStatement, Object parameter, Map<String, String> map)
            throws SQLException {
        if (parameter instanceof Integer) {
            preparedStatement.setInt(1, (int) parameter);
        } else if (parameter instanceof String) {
            preparedStatement.setString(1, (String) parameter);
        } else if (parameter instanceof Long) {
            preparedStatement.setLong(1, (Long) parameter);
        }
    }



/*    private void setParameter2Bean(PreparedStatement preparedStatement, Object parameter, Map<String, String> map) {
        if (map != null && map.size() > 0) {
            Field[] fields = parameter.getClass().getDeclaredFields();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (map.containsKey(field.getName())) {

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/


    /**
     * 处理结果
     *
     * @param resultSet sql执行结果
     * @param results   返回结果
     * @param className 类名
     * @param <E>       bean的type
     */
    @SuppressWarnings("unchecked")
    private <E> void handlerResultSet(ResultSet resultSet, List<E> results, String className) {
        Class<E> clazz;
        //通过反射拿到具体的类对象
        try {
            clazz = (Class<E>) Class.forName(className);
            while (resultSet.next()) {
                Object entity = clazz.newInstance();
                //resultSet->entity
                ReflectionUtil.resultSet2Bean(entity, resultSet);
                results.add((E) entity);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
    }
}
