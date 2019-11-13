package com.dxt.retail4sunmi.entity;

import java.util.List;

/**
 * Created by star on 2017/12/20 0020.
 */

public class QueryMemberBean {

    /**
     * restultSize : 2
     * flag : success
     * restult : [{"id":"e55c51c458e04d98a18d9e65c8d7b168","isNewRecord":false,"companycode":"001","name":"565","mobile":"18330278857","memberNo":"18330278857"},{"id":"6c425c709e6d4f8fa7a6de1965e8d296","isNewRecord":false,"companycode":"001","name":"bai","mobile":"18330278857","memberNo":"18330278857"}]
     * msg :
     */

    private int restultSize;
    private String flag;
    private String msg;
    private List<RestultBean> restult;

    public int getRestultSize() {
        return restultSize;
    }

    public void setRestultSize(int restultSize) {
        this.restultSize = restultSize;
    }

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

    public List<RestultBean> getRestult() {
        return restult;
    }

    public void setRestult(List<RestultBean> restult) {
        this.restult = restult;
    }

    public static class RestultBean {
        /**
         * id : e55c51c458e04d98a18d9e65c8d7b168
         * isNewRecord : false
         * companycode : 001
         * name : 565
         * mobile : 18330278857
         * memberNo : 18330278857
         */

        private String id;
        private boolean isNewRecord;
        private String companycode;
        private String name;
        private String mobile;
        private String memberNo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getCompanycode() {
            return companycode;
        }

        public void setCompanycode(String companycode) {
            this.companycode = companycode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMemberNo() {
            return memberNo;
        }

        public void setMemberNo(String memberNo) {
            this.memberNo = memberNo;
        }
    }
}
