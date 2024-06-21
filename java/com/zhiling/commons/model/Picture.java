package com.zhiling.commons.model;

import java.util.Date;

/**
 * @author: liyangjin
 * @create: 2023-11-22 16:41
 * @Description:
 */
public class Picture {
    private Integer id;
    private String mjId;
    private String msgId;
    private Integer type;
    private String mjUrl;
    private String ossUrl;
    private Date createTime;
    private Date updateTime;
}

/**
 create table picture (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
 `mj_id` varchar(30) NOT NULL DEFAULT '' COMMENT 'mj任务标识',
 `msg_id` varchar(30) NOT NULL DEFAULT '' COMMENT 'mj消息ID',
 `type` int(11) NOT NULL COMMENT '类型 1-底图 2-四宫格 3-放大图'
 `mj_url` varchar(300) NOT NULL DEFAULT '' COMMENT 'mj地址',
 `oss_url` varchar(300) NOT NULL DEFAULT '' COMMENT 'oss地址',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY pk_id (`id`),
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图片表';


 */
