package com.bins.binbatis.session.defaults;

import com.bins.binbatis.config.Configuration;
import com.bins.binbatis.config.MappedStatement;
import com.bins.binbatis.session.SqlSession;
import com.bins.binbatis.session.SqlSessionFactory;
import com.bins.binbatis.util.CDUtil;
import com.bins.binbatis.util.JdbcUtil;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author leo-bin
 * @date 2020/5/6 21:48
 * @apiNote 会话工厂默认实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    /**
     * 全局配置对象
     */
    private final Configuration configuration = new Configuration();
    /**
     * mappers.xml配置文件位置
     */
    private static final String MAPPER_CONFIG_LOCATION = "mappers";
    /**
     * 数据库配置文件
     */
    private static final String DB_CONFIG_FILE = "jdbc.properties";


    /**
     * init
     */
    public DefaultSqlSessionFactory() {
        loadDBInfo();
        loadMapperInfo();
        JdbcUtil.init(configuration);
    }


    /**
     * 加载db配置信息
     */
    private void loadDBInfo() {
        InputStream dbStream = this.getClass().getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
        Properties properties = new Properties();
        try {
            properties.load(dbStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration.setJdbcDriver(properties.getProperty("jdbc.driver"));
        configuration.setJdbcUrl(properties.getProperty("jdbc.url"));
        configuration.setJdbcUserName(properties.getProperty("jdbc.username"));
        configuration.setJdbcPassWord(properties.getProperty("jdbc.password"));
    }


    /**
     * 加载mappers文件夹下的所有mapper xml文件
     */
    private void loadMapperInfo() {
        String filePath = null;
        try {
            URL url = this.getClass().getClassLoader().getResource(MAPPER_CONFIG_LOCATION);
            if (url != null) {
                filePath = URLDecoder.decode(url.getPath(), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (filePath != null) {
            File mappers = new File(filePath);
            File[] files = mappers.listFiles();
            if (files != null) {
                for (File file : files) {
                    loadMapperInfo(file);
                }
            }
        }
    }


    /**
     * 加载并解析每一个mapper xml文件
     */
    private void loadMapperInfo(File file) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //拿到根节点元素<mappers>
        Element root = document.getRootElement();
        //从mapper元素中拿namespace
        String namespace = root.attribute("namespace").getData().toString();
        //获取select，insert，update，delete字段的节点集合
        List<Element> selects = root.elements("select");
        List<Element> inserts = root.elements("insert");
        List<Element> updates = root.elements("update");
        List<Element> deletes = root.elements("delete");
        List<Element> all = new ArrayList<>();
        if (!CDUtil.isEmptyList(selects)) {
            all.addAll(selects);
        }
        if (!CDUtil.isEmptyList(inserts)) {
            all.addAll(inserts);
        }
        if (!CDUtil.isEmptyList(updates)) {
            all.addAll(updates);
        }
        if (!CDUtil.isEmptyList(deletes)) {
            all.addAll(deletes);
        }
        for (Element element : all) {
            MappedStatement mappedStatement = new MappedStatement();
            String id = element.attribute("id").getData().toString();
            Attribute attribute = element.attribute("resultType");
            String resultType = attribute != null ? attribute.getData().toString() : null;
            String sql = element.getData().toString();
            mappedStatement.setId(id);
            mappedStatement.setNamespace(namespace);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSql(sql);
            configuration.getMappedStatements().put(namespace + "." + id, mappedStatement);
        }
    }



    /**
     * 根据配置信息创建一个SqlSession
     *
     * @return SqlSession
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
