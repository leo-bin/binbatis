package com.bins.test.dao;

import com.bins.test.pojo.UserInfo;

public interface UserInfoMapper {

    UserInfo selectByPrimaryKey(Integer id);

    int insert(UserInfo userInfo);

    int delete(Integer id);
}