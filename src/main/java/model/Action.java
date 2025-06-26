package com.zhiling.commons.model;

import java.util.Date;

/**
 * @author: liyangjin
 * @create: 2023-11-22 17:04
 * @Description:
 */
public class Action {
    private Integer id;
    private Integer userId;
    private Integer creationId;
    private Integer type;
    private Date createTime;
    private Date updateTime;
}

/**

 create table action (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
 `user_id` int(11) NOT NULL COMMENT '用户ID',
 `creation_id` int(11) NOT NULL DEFAULT 0 COMMENT '作品ID',
 `type` int(11) NOT NULL COMMENT '动作类型：1-登陆 2-作图 3-点赞 4-收藏 5-下载',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY pk_id (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户行为表';

 */