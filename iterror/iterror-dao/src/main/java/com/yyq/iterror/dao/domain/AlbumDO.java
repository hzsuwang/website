package com.yyq.iterror.dao.domain;

import com.yyq.iterror.common.BaseDO;
import lombok.Data;

@Data
public class AlbumDO extends BaseDO {
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String content;
    /**
     * 图标
     */
    private String icon;
    /**
     * 流览次数
     */
    private int viewNum;
    /**
     * 收藏数
     */
    private int collectNum;

    /**
     * 点赞数
     */
    private int likeNum;


    /**
     * 评论数
     */
    private int commentNum;

    /**
     *
     */
    private int catId;

    /**
     * 相对id
     */
    private String abscid;

    /**
     * 分享次数
     */
    private int shareNum;
}
