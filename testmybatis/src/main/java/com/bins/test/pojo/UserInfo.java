package com.bins.test.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserInfo implements Serializable {
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 注册时间
     */
    private Date regDate;

    private static final long serialVersionUID = 1L;
}

