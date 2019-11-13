package com.dxt.retail4sunmi.entity;

/**
 * star on 2018/4/18 0018.
 */
public class UpdateBean {
    /**
     * result : {"newVersion":"5","url":"http://192.168.4.37:8080/dxt_file_up/static/apk/1.apk","isMust":"0","isUpdate":"1"}
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
         * newVersion : 5
         * url : http://192.168.4.37:8080/dxt_file_up/static/apk/1.apk
         * isMust : 0
         * isUpdate : 1
         */

        private String newVersion;
        private String url;
        private String isMust;
        private String isUpdate;

        public String getNewVersion() {
            return newVersion;
        }

        public void setNewVersion(String newVersion) {
            this.newVersion = newVersion;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIsMust() {
            return isMust;
        }

        public void setIsMust(String isMust) {
            this.isMust = isMust;
        }

        public String getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(String isUpdate) {
            this.isUpdate = isUpdate;
        }
    }
}
