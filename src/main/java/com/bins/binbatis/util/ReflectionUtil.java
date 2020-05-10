package com.bins.binbatis.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author leo-bin
 * @date 2020/5/8 17:39
 * @apiNote 反射包
 */
public class ReflectionUtil {


    /**
     * 为指定的 bean 的 propName 属性的设为 value
     *
     * @param bean     目标对象
     * @param propName 属性
     * @param value    值
     */
    public static void setProp2Bean(Object bean, String propName, Object value) {
        Field field;
        try {
            field = bean.getClass().getDeclaredField(propName);
            //set accessible
            field.setAccessible(true);
            field.set(bean, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将结果集set进具体的JavaBean
     *
     * @param entity    bean
     * @param resultSet 结果集
     * @throws SQLException sql异常
     */
    public static void resultSet2Bean(Object entity, ResultSet resultSet) throws SQLException {
        //通过反射的方法获取对象的所有字段
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            //属性name
            String fieldName = field.getName();
            String colName = StringUtil.humpToUnderline(fieldName);
            //类型name
            String simpleName = field.getType().getSimpleName();
            if ("String".equals(simpleName)) {
                setProp2Bean(entity, fieldName, resultSet.getString(colName));
            } else if ("Integer".equals(simpleName)) {
                setProp2Bean(entity, fieldName, resultSet.getInt(colName));
            } else if ("Long".equals(simpleName)) {
                setProp2Bean(entity, fieldName, resultSet.getLong(colName));
            } else if ("Date".equals(simpleName)) {
                setProp2Bean(entity, fieldName, resultSet.getDate(colName));
            }
        }
    }
}
