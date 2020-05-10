package com.bins.test.dao;


import com.bins.binbatis.session.SqlSession;
import com.bins.binbatis.session.SqlSessionFactory;
import com.bins.binbatis.session.defaults.DefaultSqlSessionFactory;
import com.bins.test.pojo.UserInfo;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class UserInfoMapperTest {

    @Test
    public void testSelectById() {
        int id = 1;
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        System.out.println(userInfo);
    }




    @Test
    public void testFile() {
        ArrayList<String> listFileName = new ArrayList<>();
        getAllFileName("C:\\tmp\\file", listFileName);
        for (String name : listFileName) {
            if (name.contains(".txt")) {
                System.out.println(name);
            }
        }
    }




    //遍历文件夹下所有文件
    public static void getAllFileName(String path, ArrayList<String> listFileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] names = file.list();
        if (names != null) {
            String[] completNames = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                completNames[i] = path + names[i];
            }
            listFileName.addAll(Arrays.asList(completNames));
        }
    }

    //打开文件
    public static void readFile(String m) {
        String pathname = m;
        File file = new File(pathname);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String line;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
