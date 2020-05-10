#创建数据库
create database bin_batis;

use bin_batis;

# 建用户表
create table user_info
(
  id       int auto_increment primary key,
  username varchar(10) not null comment '用户名',
  password varchar(12) not null comment '密码',
  reg_date timestamp default CURRENT_TIMESTAMP comment '注册时间'
)