package com.dxt.retail4sunmi.entity;

import java.util.List;

/**
 * star on 2018/11/9 0009.
 */
public class TiketModelBean {


    /**
     * result : {"printFlag":"0","payableAmount":"97.00","tradeAssistant":{"id":"9a36842e332d11e8884c005056a82e70","name":"AA"},"proceedsAmount":"97.00","discountsAmount":"-5.00","remarks":"","vchcode":"SO974277-201811010001","companycode":"974277","otherAmount":"0.00","retailOrderInfoList":[{"activityFlag":"1","productNum":"1.00","productPrice":100,"productName":"疾速自由+双线快充移动电源Y10+ 10000mAh黑","productAmount":100},{"activityFlag":"1","productNum":"1.00","productPrice":0,"productName":"麦泡MIPOW移动电源10000毫安SPL10-玫瑰金 UP+","productAmount":0},{"activityFlag":"1","productNum":"1.00","productPrice":2,"productName":"会说话的驴 抖音 UP+","productAmount":2},{"activityFlag":"0","productPrice":-5,"productName":"满减"}],"printModelInfoList":[{"text":"店名:Up+商户（勿动）","flag":"0","styleflag":"0","lineNo":"1"},{"text":"订单编号:SO974277-201811010001","flag":"0","styleflag":"0","lineNo":"2"},{"text":"制单日期:2018-11-01 15:10:31","flag":"0","styleflag":"1","lineNo":"3"},{"text":"付款方式1:支付宝二维码支付  0.01元","flag":"0","styleflag":"0","lineNo":"4"},{"text":"付款方式2:银行卡  1.0元","flag":"0","styleflag":"0","lineNo":"4"},{"text":"付款方式3:微信二维码支付  0.01元","flag":"0","styleflag":"0","lineNo":"4"},{"text":"付款方式4:现金  95.98元","flag":"0","styleflag":"0","lineNo":"4"},{"text":"自定义文本测试","flag":"1","styleflag":"0","lineNo":"5"},{"text":"如需开票，请扫描下方二维码","flag":"1","styleflag":"1","lineNo":"6"},{"text":"订单主体","flag":"3","styleflag":"0","lineNo":"7"},{"text":"http://www.baidu.com","flag":"2","styleflag":"1","lineNo":"8"}],"retailPays":[{"payMoney":0.01,"changeMoney":"","shroffMethod":"支付宝二维码支付","orderNo":"","terminalNo":"","traceNo":"","shroffType":"7","transNo":"2018110122001449301008919269"},{"payMoney":1,"changeMoney":"","shroffMethod":"银行卡","orderNo":"","terminalNo":"","traceNo":"","shroffType":"5","transNo":""},{"payMoney":0.01,"changeMoney":"","shroffMethod":"微信二维码支付","orderNo":"","terminalNo":"","traceNo":"","shroffType":"8","transNo":""},{"payMoney":95.98,"changeMoney":0,"shroffMethod":"现金","orderNo":"","terminalNo":"","traceNo":"","shroffType":"1","transNo":""}],"retailDiscountsInfoList":[],"productNum":"3.00","createDate":1541056231000,"orderStatue":"18","productAmount":"102.00"}
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
         * printFlag : 0
         * payableAmount : 97.00
         * tradeAssistant : {"id":"9a36842e332d11e8884c005056a82e70","name":"AA"}
         * proceedsAmount : 97.00
         * discountsAmount : -5.00
         * remarks :
         * vchcode : SO974277-201811010001
         * companycode : 974277
         * otherAmount : 0.00
         * retailOrderInfoList : [{"activityFlag":"1","productNum":"1.00","productPrice":100,"productName":"疾速自由+双线快充移动电源Y10+ 10000mAh黑","productAmount":100},{"activityFlag":"1","productNum":"1.00","productPrice":0,"productName":"麦泡MIPOW移动电源10000毫安SPL10-玫瑰金 UP+","productAmount":0},{"activityFlag":"1","productNum":"1.00","productPrice":2,"productName":"会说话的驴 抖音 UP+","productAmount":2},{"activityFlag":"0","productPrice":-5,"productName":"满减"}]
         * printModelInfoList : [{"text":"店名:Up+商户（勿动）","flag":"0","styleflag":"0","lineNo":"1"},{"text":"订单编号:SO974277-201811010001","flag":"0","styleflag":"0","lineNo":"2"},{"text":"制单日期:2018-11-01 15:10:31","flag":"0","styleflag":"1","lineNo":"3"},{"text":"付款方式1:支付宝二维码支付  0.01元","flag":"0","styleflag":"0","lineNo":"4"},{"text":"付款方式2:银行卡  1.0元","flag":"0","styleflag":"0","lineNo":"4"},{"text":"付款方式3:微信二维码支付  0.01元","flag":"0","styleflag":"0","lineNo":"4"},{"text":"付款方式4:现金  95.98元","flag":"0","styleflag":"0","lineNo":"4"},{"text":"自定义文本测试","flag":"1","styleflag":"0","lineNo":"5"},{"text":"如需开票，请扫描下方二维码","flag":"1","styleflag":"1","lineNo":"6"},{"text":"订单主体","flag":"3","styleflag":"0","lineNo":"7"},{"text":"http://www.baidu.com","flag":"2","styleflag":"1","lineNo":"8"}]
         * retailPays : [{"payMoney":0.01,"changeMoney":"","shroffMethod":"支付宝二维码支付","orderNo":"","terminalNo":"","traceNo":"","shroffType":"7","transNo":"2018110122001449301008919269"},{"payMoney":1,"changeMoney":"","shroffMethod":"银行卡","orderNo":"","terminalNo":"","traceNo":"","shroffType":"5","transNo":""},{"payMoney":0.01,"changeMoney":"","shroffMethod":"微信二维码支付","orderNo":"","terminalNo":"","traceNo":"","shroffType":"8","transNo":""},{"payMoney":95.98,"changeMoney":0,"shroffMethod":"现金","orderNo":"","terminalNo":"","traceNo":"","shroffType":"1","transNo":""}]
         * retailDiscountsInfoList : []
         * productNum : 3.00
         * createDate : 1541056231000
         * orderStatue : 18
         * productAmount : 102.00
         */

        private String printFlag;
        private String printNum;

        public String getPrintNum() {
            return printNum;
        }

        public void setPrintNum(String printNum) {
            this.printNum = printNum;
        }

        private String payableAmount;
        private TradeAssistantBean tradeAssistant;
        private String proceedsAmount;
        private String discountsAmount;
        private String remarks;
        private String vchcode;
        private String companycode;
        private String otherAmount;
        private String productNum;
        private long createDate;
        private String orderStatue;
        private String productAmount;
        private List<RetailOrderInfoListBean> retailOrderInfoList;
        private List<PrintModelInfoListBean> printModelInfoList;
        private List<RetailPaysBean> retailPays;
        private List<?> retailDiscountsInfoList;

        public String getPrintFlag() {
            return printFlag;
        }

        public void setPrintFlag(String printFlag) {
            this.printFlag = printFlag;
        }

        public String getPayableAmount() {
            return payableAmount;
        }

        public void setPayableAmount(String payableAmount) {
            this.payableAmount = payableAmount;
        }

        public TradeAssistantBean getTradeAssistant() {
            return tradeAssistant;
        }

        public void setTradeAssistant(TradeAssistantBean tradeAssistant) {
            this.tradeAssistant = tradeAssistant;
        }

        public String getProceedsAmount() {
            return proceedsAmount;
        }

        public void setProceedsAmount(String proceedsAmount) {
            this.proceedsAmount = proceedsAmount;
        }

        public String getDiscountsAmount() {
            return discountsAmount;
        }

        public void setDiscountsAmount(String discountsAmount) {
            this.discountsAmount = discountsAmount;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

        public String getOtherAmount() {
            return otherAmount;
        }

        public void setOtherAmount(String otherAmount) {
            this.otherAmount = otherAmount;
        }

        public String getProductNum() {
            return productNum;
        }

        public void setProductNum(String productNum) {
            this.productNum = productNum;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getOrderStatue() {
            return orderStatue;
        }

        public void setOrderStatue(String orderStatue) {
            this.orderStatue = orderStatue;
        }

        public String getProductAmount() {
            return productAmount;
        }

        public void setProductAmount(String productAmount) {
            this.productAmount = productAmount;
        }

        public List<RetailOrderInfoListBean> getRetailOrderInfoList() {
            return retailOrderInfoList;
        }

        public void setRetailOrderInfoList(List<RetailOrderInfoListBean> retailOrderInfoList) {
            this.retailOrderInfoList = retailOrderInfoList;
        }

        public List<PrintModelInfoListBean> getPrintModelInfoList() {
            return printModelInfoList;
        }

        public void setPrintModelInfoList(List<PrintModelInfoListBean> printModelInfoList) {
            this.printModelInfoList = printModelInfoList;
        }

        public List<RetailPaysBean> getRetailPays() {
            return retailPays;
        }

        public void setRetailPays(List<RetailPaysBean> retailPays) {
            this.retailPays = retailPays;
        }

        public List<?> getRetailDiscountsInfoList() {
            return retailDiscountsInfoList;
        }

        public void setRetailDiscountsInfoList(List<?> retailDiscountsInfoList) {
            this.retailDiscountsInfoList = retailDiscountsInfoList;
        }

        public static class TradeAssistantBean {
            /**
             * id : 9a36842e332d11e8884c005056a82e70
             * name : AA
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class RetailOrderInfoListBean {
            /**
             * activityFlag : 1
             * productNum : 1.00
             * productPrice : 100.0
             * productName : 疾速自由+双线快充移动电源Y10+ 10000mAh黑
             * productAmount : 100.0
             */

            private String activityFlag;
            private String productNum;
            private double productPrice;
            private String productName;
            private double productAmount;

            public String getActivityFlag() {
                return activityFlag;
            }

            public void setActivityFlag(String activityFlag) {
                this.activityFlag = activityFlag;
            }

            public String getProductNum() {
                return productNum;
            }

            public void setProductNum(String productNum) {
                this.productNum = productNum;
            }

            public double getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(double productPrice) {
                this.productPrice = productPrice;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public double getProductAmount() {
                return productAmount;
            }

            public void setProductAmount(double productAmount) {
                this.productAmount = productAmount;
            }
        }

        public static class PrintModelInfoListBean {
            /**
             * text : 店名:Up+商户（勿动）
             * flag : 0
             * styleflag : 0
             * lineNo : 1
             */

            private String text;
            private String flag;
            private String styleflag;
            private String lineNo;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getStyleflag() {
                return styleflag;
            }

            public void setStyleflag(String styleflag) {
                this.styleflag = styleflag;
            }

            public String getLineNo() {
                return lineNo;
            }

            public void setLineNo(String lineNo) {
                this.lineNo = lineNo;
            }
        }

        public static class RetailPaysBean {
            /**
             * payMoney : 0.01
             * changeMoney :
             * shroffMethod : 支付宝二维码支付
             * orderNo :
             * terminalNo :
             * traceNo :
             * shroffType : 7
             * transNo : 2018110122001449301008919269
             */

            private double payMoney;
            private String changeMoney;
            private String shroffMethod;
            private String orderNo;
            private String terminalNo;
            private String traceNo;
            private String shroffType;
            private String transNo;

            public double getPayMoney() {
                return payMoney;
            }

            public void setPayMoney(double payMoney) {
                this.payMoney = payMoney;
            }

            public String getChangeMoney() {
                return changeMoney;
            }

            public void setChangeMoney(String changeMoney) {
                this.changeMoney = changeMoney;
            }

            public String getShroffMethod() {
                return shroffMethod;
            }

            public void setShroffMethod(String shroffMethod) {
                this.shroffMethod = shroffMethod;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getTerminalNo() {
                return terminalNo;
            }

            public void setTerminalNo(String terminalNo) {
                this.terminalNo = terminalNo;
            }

            public String getTraceNo() {
                return traceNo;
            }

            public void setTraceNo(String traceNo) {
                this.traceNo = traceNo;
            }

            public String getShroffType() {
                return shroffType;
            }

            public void setShroffType(String shroffType) {
                this.shroffType = shroffType;
            }

            public String getTransNo() {
                return transNo;
            }

            public void setTransNo(String transNo) {
                this.transNo = transNo;
            }
        }
    }
}
