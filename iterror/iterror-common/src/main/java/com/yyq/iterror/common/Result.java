package com.yyq.iterror.common;

import lombok.Data;

import java.util.Optional;

@Data
public class Result<T> implements java.io.Serializable {

    /**
     * 返回码：0-成功，其他-异常
     */
    private long   rc;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据包
     */
    private T      data;

    /**
     * 构造器
     *
     * @param resultCode 返回码
     */
    public Result(ResultCode resultCode){
        resultCode = Optional.ofNullable(resultCode).orElse(ResultCode.ERROR);
        this.rc = resultCode.getRc();
        this.msg = resultCode.getMsg();
    }

    public Result(ResultCode resultCode, T data){
        resultCode = Optional.ofNullable(resultCode).orElse(ResultCode.ERROR);
        this.rc = resultCode.getRc();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> ok() {
        return new Result<T>(ResultCode.SUCCESS);
    }

    public static <T> Result<T> fail() {
        return Result.fail(ResultCode.ERROR);
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> result = Result.fail(ResultCode.ERROR);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> fail(long rc, String msg) {
        Result<T> result = Result.fail(ResultCode.ERROR);
        result.setMsg(msg);
        result.setRc(rc);
        return result;
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    /**
     * 是否成功
     *
     * @return
     */
    public Boolean success() {
        return this.rc == ResultCode.SUCCESS.getRc();
    }
}
