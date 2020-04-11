package com.yyq.iterror.dao.domain;

import com.yyq.iterror.common.BaseDO;
import lombok.Data;

@Data
public class CatDO extends BaseDO {

    /**
     *  名称
     */
    private String name;

    /**
     *  描述
     */
    private String describe;
    /**
     * 上级id
     */
    private long pid;
    /**
     * 类型
     */
    private int type;
    /**
     * 显示顺序
     */
    private int           orderNum;
    /**
     * 相对id
     */
    private String absid;



}
