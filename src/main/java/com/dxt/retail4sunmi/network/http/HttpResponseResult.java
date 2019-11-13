package com.dxt.retail4sunmi.network.http;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/25.
 */

public class HttpResponseResult<T> {

    private static final int SUCCESS_CODE = 200;

    private int status;
    private Integer errorCode;
    private T data;

    public boolean isSuccess() {
        return status == SUCCESS_CODE;
    }

    public String getMsg() {
        return "系统异常:errorCode=" + errorCode;
    }

    public Integer getState() {
        return status;
    }

    public T getResult() {
        return data;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", status=" + status +
                ", result=" + data +
                '}';
    }
}
