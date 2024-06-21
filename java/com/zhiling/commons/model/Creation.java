package com.zhiling.commons.model;

import java.util.Date;

/**
 * @author: liyangjin
 * @create: 2023-11-22 16:58
 * @Description:
 */
public class Creation {
    private Integer id;
    private Integer userId;
    private Integer basePicId;
    private String prompt;
    private Integer finishPicId;
    private Integer viewCnt;
    private Integer likeCnt;
    private Integer collectCnt;
    private Integer downloadCnt;
    private Date createTime;
    private Date updateTime;
}

/**
 create table creation (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
 `user_id` int(11) NOT NULL COMMENT '用户ID',
 `base_pic_id` int(11) NOT NULL DEFAULT 0 COMMENT '底图',
 `prompt` varchar(300) NOT NULL DEFAULT '' COMMENT '提示词',
 `finish_pic_id` int(11) NOT NULL DEFAULT 0 COMMENT '成品图',
 `view_cnt` int(11) NOT NULL DEFAULT 0 COMMENT '浏览量',
 `like_cnt` int(11) NOT NULL DEFAULT 0 COMMENT '点赞量',
 `collect_cnt` int(11) NOT NULL DEFAULT 0 COMMENT '收藏量',
 `download_cnt` int(11) NOT NULL DEFAULT 0 COMMENT '下载量',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY pk_id (`id`),
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='创作表';



 */