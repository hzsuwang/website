package com.yyq.iterror.dao.domain;

import com.yyq.iterror.common.BaseDO;
import lombok.Data;

@Data
public class AlbumQuestionDO extends BaseDO {
    /**
     * 专辑id
     */
    private long albumId;
    /**
     * 问题id
     */
    private long qid;
    /**
     * 显示顺序
     */
    private int orderNum;
}
