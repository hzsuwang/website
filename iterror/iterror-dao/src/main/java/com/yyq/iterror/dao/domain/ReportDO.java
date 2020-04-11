package com.yyq.iterror.dao.domain;

import com.yyq.iterror.common.BaseDO;
import lombok.Data;

@Data
public class ReportDO extends BaseDO {
    /**
     * 举报的用户id
     */
    private long userId;
    /**
     *  对象id
     */
    private long oid;
    /**
     * 对象类型
     */
    private int otype;
    /**
     * 举报理由
     */
    private String reason;
    /**
     * 处理状态
     */
    private int status;
    /**
     * 审核人员
     */
    private long verifyUid;
    /**
     * 审核说明
     */
    private long verifyDesc;

}
