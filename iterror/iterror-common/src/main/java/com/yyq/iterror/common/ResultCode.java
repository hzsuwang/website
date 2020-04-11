package com.yyq.iterror.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(1, "执行成功"),
    CODE_SESSION_OVERDUE(-1, "会话已过期，请重新登陆"),
    FORBIDEN(-2, "您已被封号"),
    PARAMS_ERR(-9, "参数有误"),
    NOT_FOUND (-10, "资源不存在"),
    PERMISSION_ERR(-11, "权限不足"),
    ERROR(-999, "执行异常，请重试!"),
    ;
    private final int   rc;
    private final String msg;
}
