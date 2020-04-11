package com.yyq.iterror.dao.domain;

import com.yyq.iterror.common.BaseDO;
import lombok.Data;

import java.util.Date;

@Data
public class FollowDO extends BaseDO {
    /**
     *  用户id
     */
    private long userId;
    /**
     * 对象id
     */
    private long oid;
    /**
     * 关注的类型
     */
    private int followType;

    /**
     *  用户最后看的时间
     */
    private Date lastTime;
}
