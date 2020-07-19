package com.bins.binbatis.config;

/**
 * @author leo-bin
 * @date 2020/5/6 22:02
 * @apiNote 存放一个sql映射字段，类似于：<select> select ...</select>
 */
public class MappedStatement {

    /**
     * sql字段所在的命名空间
     */
    private String namespace;
    /**
     * sql字段的id
     */
    private String id;
    /**
     * Sql执行结果类型的全限定名
     */
    private String resultType;
    /**
     * 具体的sql语句
     */
    private String sql;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
