package com.dxt.retail4sunmi.entity;

/**
 * star on 2018/5/25 0025.
 */
public class QueyOrderStateBean {
    /**
     * result : {"status":"21","vchcode":"SO974277-201805250007","companycode":"974277"}
     * flag : success
     * msg :
     */

    private ResultBean result;
    private String flag;
    private String msg;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
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

    public static class ResultBean {
        /**
         * status : 21
         * vchcode : SO974277-201805250007
         * companycode : 974277
         */

        private String status;
        private String vchcode;
        private String companycode;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVchcode() {
            return vchcode;
        }

        public void setVchcode(String vchcode) {
            this.vchcode = vchcode;
        }

        public String getCompanycode() {
            return companycode;
        }

        public void setCompanycode(String companycode) {
            this.companycode = companycode;
        }
    }
}
