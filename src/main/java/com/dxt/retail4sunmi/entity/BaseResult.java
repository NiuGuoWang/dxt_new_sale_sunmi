package com.dxt.retail4sunmi.entity;

/**
 * Created by star on 2017/10/13 0013.
 */

public class BaseResult {
    private String status;
    private String msg;
    /**
     * flag : success
     */

    private String flag;

    @Override
    public String toString() {
        return "BaseResult{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
