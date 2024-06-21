package com.zhiling.commons.model;

import java.util.Date;

/**
 * @author: liyangjin
 * @create: 2023-11-22 16:28
 * @Description:
 */
public class User {
    private Integer id;
    private String name;
    private String phone;
    private String passwordMd5;
    private Date createTime;
    private Date updateTime;
}
/**
 *
 create table user (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
 `name` varchar(20) NOT NULL COMMENT '昵称',
 `phone` varchar(11) NOT NULL COMMENT '手机号',
 `password_md5` varchar(20) NOT NULL COMMENT '密码小写md5',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY pk_id (`id`),
 UNIQUE_KEY uk_phone (`phone`),
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

*/