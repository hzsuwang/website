package com.yyq.iterror.dao.domain;

import com.yyq.iterror.common.BaseDO;
import lombok.Data;

@Data
public class CommentDO extends BaseDO {
    /**
     * 用户id
     */
    private long userId;
    /**
     * 评论对象的类型
     */
    private int otype;
    /**
     * 对象id
     */
    private long oid;
    /**
     *  回复来源id
     */
    private long refId;
    /**
     * 内容
     */
    private String content;
    /**
     * 状态
     */
    private int status;
    /**
     * 点赞数
     */
    private int likeNum;

    /**
     * 置顶
     */
    private int top;
}
