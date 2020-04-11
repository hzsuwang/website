package com.yyq.iterror.dao.domain;

import com.yyq.iterror.common.BaseDO;
import lombok.Data;

@Data
public class AnswerDO extends BaseDO {
    private long userId;

    /**
     * 回答正文
     */
    private String content;


    /**
     * 审核状态
     */
    private int status;

    /**
     * 问题ID
     */
    private long qid;

    /**
     * 点赞数
     */
    private int likeNum;

    /**
     * 收藏数
     */
    private int collectNum;

    /**
     * 查看次数
     */
    private int viewNum;

    /**
     * 评论数
     */
    private int commentNum;
}
