package com.yyq.iterror.dao.domain;

import com.yyq.iterror.common.BaseDO;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionDO extends BaseDO {
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 审核状态 1通过  0不通过
     */
    private int status;

    /**
     * 标题
     */
    private String title;
    /**
     * 问题描述
     */
    private String content;

    /**
     * 类型
     */
    private int type;
    /**
     * 分类id
     */
    private long catId;
    /**
     * 标签相对id
     */
    private String abscid;

    /**
     * 回答数
     */
    private int answerNum ;

    /**
     * 流览次数
     */
    private int viewNum;

    /**
     * 关注数
     */
    private int followNum;

    /**
     * 收藏数
     */
    private int collectNum;

    /**
     * 分享次数
     */
    private long shareNum;

    /**
     * 最后回答时间
     */
    private Date lastAnswerTime;
}
