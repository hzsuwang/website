package com.yyq.iterror.common;

import lombok.Data;
import java.util.Date;

@Data
public class BaseDO implements java.io.Serializable{
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * 修改时间
     */
    protected Date editTime;

    /**
     * 删除标识：0-未删除(FALSE)，1-已删除(TRUE)
     */
    protected boolean deleted;
}
