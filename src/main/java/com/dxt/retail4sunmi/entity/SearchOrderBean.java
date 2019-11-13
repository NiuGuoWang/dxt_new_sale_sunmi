package com.dxt.retail4sunmi.entity;

import java.util.List;

/**
 * Created by star on 2018/1/3 0003.
 */

public class SearchOrderBean {

    /**
     * result : [{"id":"038d4ffc33ee491f8451367c68b27bcb","status":"16","billDate":"2017-12-29 18:38:38","vchcode":"SO974267-201712290043"},{"id":"f690572e6724425c9fea696325ff542d","status":"16","billDate":"2017-12-29 18:34:40","vchcode":"SO974267-201712290042"},{"id":"819f03f86c25442c8955ef8225be27aa","status":"16","billDate":"2017-12-29 18:28:16","vchcode":"SO974267-201712290039"},{"id":"fb24f0d0ad5d4150bb4bc7d98d0648a1","status":"16","billDate":"2017-12-29 14:16:52","vchcode":"SO974267-201712290016"},{"id":"85af99a155ef4edb8796968824c892b2","status":"16","billDate":"2017-12-29 14:05:24","vchcode":"SO974267-201712290014"},{"id":"7ec5e644de184f848432cefd3dc333de","status":"16","billDate":"2017-12-29 10:30:15","vchcode":"SO974267-201712290010"},{"id":"406d7a3a41a5413f8d7e5ec81d3c4129","status":"16","billDate":"2017-12-29 10:27:14","vchcode":"SO974267-201712290006"},{"id":"b6ad27c70b044cf2abb2fe05724da5aa","status":"16","billDate":"2017-12-28 20:55:38","vchcode":"SO974267-201712280034"},{"id":"0afe54e4e3fc4b218f48d1a16e0270c1","status":"16","billDate":"2017-12-28 20:49:37","vchcode":"SO974267-201712280033"},{"id":"3c6e19dd20634fc2ab719d59569caac6","status":"16","billDate":"2017-12-28 17:18:28","vchcode":"SO974267-201712280013"}]
     * flag : success
     * msg :
     */

    private String flag;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 038d4ffc33ee491f8451367c68b27bcb
         * status : 16
         * billDate : 2017-12-29 18:38:38
         * vchcode : SO974267-201712290043
         */

        private String id;
        private String status;
        private String createDate;
        private String vchcode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getVchcode() {
            return vchcode;
        }

        public void setVchcode(String vchcode) {
            this.vchcode = vchcode;
        }
    }
}
