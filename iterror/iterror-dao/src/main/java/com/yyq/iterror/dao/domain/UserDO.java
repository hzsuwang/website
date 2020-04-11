package com.yyq.iterror.dao.domain;

import com.yyq.iterror.common.BaseDO;
import lombok.Data;

@Data
public class UserDO extends BaseDO {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 名称
     */
    private String name;
    /**
     * 密码
     */
    private String passwd;
    /**
     * 邮箱地扯
     */
    private String email;
}
