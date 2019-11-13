package com.dxt.retail4sunmi.entity;

import java.util.List;

/**
 * Created by liu04 on 2017/12/15.
 */

public class SearchGoodsBean {

    /**
     * result : [{"id":"9a09d13e23634ccfb80bb69252a06635","retailPrice":3000,"name":"华为荣耀","imeiManage":"1","barCode":"HWRY"},{"id":"7845d1b2e999450589d8a1f7166c6810","retailPrice":6000,"name":"苹果S3","imeiManage":"1","barCode":"PG3"},{"id":"7ceffd0d35b8495b802d9eba3f6b5691","retailPrice":2000,"name":"小米S1","imeiManage":"1","barCode":"XM1"}]
     * flag : success
     * msg :
     */

    private String flag;
    private String msg;
    private List<GoodsBean> result;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<GoodsBean> getResult() {
        return result;
    }

    public void setResult(List<GoodsBean> result) {
        this.result = result;
    }
}
