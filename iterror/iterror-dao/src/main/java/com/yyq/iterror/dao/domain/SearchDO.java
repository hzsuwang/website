package com.yyq.iterror.dao.domain;

import com.yyq.iterror.common.BaseDO;
import lombok.Data;

@Data
public class SearchDO extends BaseDO {
    /**
     * 搜索的关键词
     */
    private String keyword;
    /**
     *  搜索次数
     */
    private int searchNum;


}
