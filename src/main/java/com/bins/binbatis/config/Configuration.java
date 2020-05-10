package com.bins.binbatis.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leo-bin
 * @date 2020/5/6 21:58
 * @apiNote 全局配置类
 */
public class Configuration {

    /**
     * jdbc驱动
     */
    private String jdbcDriver;
    /**
     * jdbc url
     */
    private String jdbcUrl;
    /**
     * userName
     */
    private String jdbcUserName;
    /**
     * passWord
     */
    private String jdbcPassWord;
    /**
     * 存放所有的Sql映射文件中的sql配置
     */
    private Map<String ,MappedStatement> mappedStatements=new HashMap<>(16);


    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUserName() {
        return jdbcUserName;
    }

    public void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

    public String getJdbcPassWord() {
        return jdbcPassWord;
    }

    public void setJdbcPassWord(String jdbcPassWord) {
        this.jdbcPassWord = jdbcPassWord;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }
}
