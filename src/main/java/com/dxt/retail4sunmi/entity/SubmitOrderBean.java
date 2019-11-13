package com.dxt.retail4sunmi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by star on 2017/12/20 0020.
 */

public class SubmitOrderBean implements Parcelable {
    @Override
    public String toString() {
        return "SubmitOrderBean{" +
                "result=" + result +
                ", flag='" + flag + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    /**
     * result : {"payableAmount":4277.5,"warehouseInfo":{"id":"527d4110be35459098bfb4a7ebfff5de"},"billDate":"2017-12-20 21:06:37","discountsAmount":-1277.5,"remarks":"","companycode":"1002","preparedBy":{"id":"7836b440393643448d4dae6e771117e2"},"id":"61d1681e0e56483fb188c8ed3b77f12e","retailOrderInfoList":[{"id":"dd939c8c2af748dd9476eb3bfa0eb9f7","activityFlag":"","productSellingPrice":"5000.0","imei":"","imeiManage":"0","productNum":"1","productMemberPrice":"","barCode":"666","productPrice":"5000.0","productName":"接口测试商品串号","productAmount":"5000.0","productId":"b12191b9a5624b27b98c7bd14d54eb63"},{"id":"20e598b565c142f090398cedfdab4995","activityFlag":"","productSellingPrice":"5555.0","imei":"","imeiManage":"0","productNum":"1","productMemberPrice":"","barCode":"222","productPrice":"5555.0","productName":"测试测试测试","productAmount":"5555.0","productId":"f7c6f09d16c141a88e275bbf5f539d4f"},{"id":"","activityFlag":"0","productSellingPrice":"","imei":"","imeiManage":"","productNum":"1","productMemberPrice":"","barCode":"","productPrice":"-1055.50","productName":"满100打9折","productAmount":"-1055.50","productId":"76ffb3228afc4b39af9ec956a4a06eea"},{"id":"","activityFlag":"0","productSellingPrice":"","imei":"","imeiManage":"","productNum":"1","productMemberPrice":"","barCode":"","productPrice":"-222.0","productName":"5555","productAmount":"-222.0","productId":"a84510e995eb4020b0698daa756aff9b"}],"retailDiscountsInfoList":[],"productNum":2,"memberId":"","comment":"SO1002-201712200074","createDate":"2017-12-20 21:05:36","productAmount":5555}
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

    public static class ResultBean implements Parcelable {

        /**
         * payableAmount : 4277.5
         * warehouseInfo : {"id":"527d4110be35459098bfb4a7ebfff5de"}
         * billDate : 2017-12-20 21:06:37
         * discountsAmount : -1277.5
         * remarks :
         * companycode : 1002
         * preparedBy : {"id":"7836b440393643448d4dae6e771117e2"}
         * id : 61d1681e0e56483fb188c8ed3b77f12e
         * retailOrderInfoList : [{"id":"dd939c8c2af748dd9476eb3bfa0eb9f7","activityFlag":"","productSellingPrice":"5000.0","imei":"","imeiManage":"0","productNum":"1","productMemberPrice":"","barCode":"666","productPrice":"5000.0","productName":"接口测试商品串号","productAmount":"5000.0","productId":"b12191b9a5624b27b98c7bd14d54eb63"},{"id":"20e598b565c142f090398cedfdab4995","activityFlag":"","productSellingPrice":"5555.0","imei":"","imeiManage":"0","productNum":"1","productMemberPrice":"","barCode":"222","productPrice":"5555.0","productName":"测试测试测试","productAmount":"5555.0","productId":"f7c6f09d16c141a88e275bbf5f539d4f"},{"id":"","activityFlag":"0","productSellingPrice":"","imei":"","imeiManage":"","productNum":"1","productMemberPrice":"","barCode":"","productPrice":"-1055.50","productName":"满100打9折","productAmount":"-1055.50","productId":"76ffb3228afc4b39af9ec956a4a06eea"},{"id":"","activityFlag":"0","productSellingPrice":"","imei":"","imeiManage":"","productNum":"1","productMemberPrice":"","barCode":"","productPrice":"-222.0","productName":"5555","productAmount":"-222.0","productId":"a84510e995eb4020b0698daa756aff9b"}]
         * retailDiscountsInfoList : []
         * productNum : 2
         * memberId :
         * vc : SO1002-201712200074
         * createDate : 2017-12-20 21:05:36
         * productAmount : 5555.0
         */

        private String payableAmount;
        private String esStatus;

        public String getEsStatus() {
            return esStatus;
        }

        public void setEsStatus(String esStatus) {
            this.esStatus = esStatus;
        }

        private WarehouseInfoBean warehouseInfo;
        private MemberBean member;
        private String billType;

        public String getBillType() {
            return billType;
        }

        public void setBillType(String billType) {
            this.billType = billType;
        }

        private TradeAssistant tradeAssistant;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public TradeAssistant getTradeAssistant() {
            return tradeAssistant;
        }

        public void setTradeAssistant(TradeAssistant tradeAssistant) {
            this.tradeAssistant = tradeAssistant;
        }

        public String getOrderStatue() {
            return orderStatue;
        }

        public void setOrderStatue(String orderStatue) {
            this.orderStatue = orderStatue;
        }

        public void setRetailPays(List<RetailPayBean> retailPays) {
            this.retailPays = retailPays;
        }

        public void setMemberBean(MemberBean memberBean) {
            this.member = memberBean;
        }


        public MemberBean getMemberBean() {
            return member;
        }

        private String billDate;
        private String discountsAmount;
        private String proceedsAmount;
        private String endpayAmount;

        public String getEndpayAmount() {
            return endpayAmount;
        }

        public String getProceedsAmount() {
            return proceedsAmount;
        }

        public void setProceedsAmount(String proceedsAmount) {
            this.proceedsAmount = proceedsAmount;
        }

        private String remarks = "";
        private String companycode;
        private PreparedByBean preparedBy;
        private String id;
        private String productNum;
        private String vchcode;
        private String orderStatue;

        public String getStatus() {
            return orderStatue;
        }

        public void setStatus(String status) {
            this.orderStatue = status;
        }

        private String createDate;
        private String productAmount;
        private String otherAmount;
        private String sjskAmount;

        public void setEndpayAmount(String endpayAmount) {
            this.endpayAmount = endpayAmount;
        }

        public String getSjskAmount() {
            return sjskAmount;
        }

        public void setSjskAmount(String sjskAmount) {
            this.sjskAmount = sjskAmount;
        }

        private List<RetailOrderInfoListBean> retailOrderInfoList;
        private List<RetailDiscountsInfoListBean> retailDiscountsInfoList = new ArrayList<>();

        public List<RetailPayBean> getRetailPays() {
            return retailPays;
        }

        private List<RetailPayBean> retailPays = new ArrayList<>();

        public String getPayableAmount() {
            return payableAmount;
        }

        public void setPayableAmount(String payableAmount) {
            this.payableAmount = payableAmount;
        }

        public WarehouseInfoBean getWarehouseInfo() {
            return warehouseInfo;
        }

        public void setWarehouseInfo(WarehouseInfoBean warehouseInfo) {
            this.warehouseInfo = warehouseInfo;
        }

        public String getBillDate() {
            return billDate;
        }

        public void setBillDate(String billDate) {
            this.billDate = billDate;
        }

        public String getOtherAmount() {
            return otherAmount;
        }

        public void setOtherAmount(String otherAmount) {
            this.otherAmount = otherAmount;
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

        public String getCompanycode() {
            return companycode;
        }

        public void setCompanycode(String companycode) {
            this.companycode = companycode;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "payableAmount=" + payableAmount +
                    ", warehouseInfo=" + warehouseInfo +
                    ", memberBean=" + member +
                    ", billDate='" + billDate + '\'' +
                    ", discountsAmount=" + discountsAmount +
                    ", remarks='" + remarks + '\'' +
                    ", companycode='" + companycode + '\'' +
                    ", preparedBy=" + preparedBy +
                    ", id='" + id + '\'' +
                    ", productNum=" + productNum +
                    ", vchcode='" + vchcode + '\'' +
                    ", createDate='" + createDate + '\'' +
                    ", productAmount=" + productAmount +
                    ", otherAmount=" + otherAmount +
                    ", retailOrderInfoList=" + retailOrderInfoList +
                    ", retailDiscountsInfoList=" + retailDiscountsInfoList +
                    '}';
        }

        public PreparedByBean getPreparedBy() {
            return preparedBy;
        }

        public void setPreparedBy(PreparedByBean preparedBy) {
            this.preparedBy = preparedBy;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductNum() {
            return productNum;
        }

        public void setProductNum(String productNum) {
            this.productNum = productNum;
        }


        public String getVchcode() {
            return vchcode;
        }

        public void setVchcode(String vchcode) {
            this.vchcode = vchcode;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
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

        public List<RetailDiscountsInfoListBean> getRetailDiscountsInfoList() {
            return retailDiscountsInfoList;
        }

        public void setRetailDiscountsInfoList(List<RetailDiscountsInfoListBean> retailDiscountsInfoList) {
            this.retailDiscountsInfoList = retailDiscountsInfoList;
        }

        public static class WarehouseInfoBean implements Parcelable {
            @Override
            public String toString() {
                return "{" +
                        "id='" + id + '\'' +
                        '}';
            }

            /**
             * id : 527d4110be35459098bfb4a7ebfff5de
             */

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
            }

            WarehouseInfoBean() {
            }

            WarehouseInfoBean(Parcel in) {
                this.id = in.readString();
            }

            public static final Creator<WarehouseInfoBean> CREATOR = new Creator<WarehouseInfoBean>() {
                @Override
                public WarehouseInfoBean createFromParcel(Parcel source) {
                    return new WarehouseInfoBean(source);
                }

                @Override
                public WarehouseInfoBean[] newArray(int size) {
                    return new WarehouseInfoBean[size];
                }
            };
        }

        public static class MemberBean implements Parcelable {
            @Override
            public String toString() {
                return "{" +
                        "id='" + id + '\'' +
                        '}';
            }

            /**
             * id : 527d4110be35459098bfb4a7ebfff5de
             */

            private String id = "";
            private String name = "";
            private String mobile = "";

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            MemberBean() {
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.name);
                dest.writeString(this.mobile);
            }

            protected MemberBean(Parcel in) {
                this.id = in.readString();
                this.name = in.readString();
                this.mobile = in.readString();
            }

            public static final Creator<MemberBean> CREATOR = new Creator<MemberBean>() {
                @Override
                public MemberBean createFromParcel(Parcel source) {
                    return new MemberBean(source);
                }

                @Override
                public MemberBean[] newArray(int size) {
                    return new MemberBean[size];
                }
            };
        }

        public static class TradeAssistant implements Parcelable {
            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public static Creator<TradeAssistant> getCREATOR() {
                return CREATOR;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.name);
                dest.writeString(this.id);
            }

            public TradeAssistant() {
            }

            protected TradeAssistant(Parcel in) {
                this.name = in.readString();
                this.id = in.readString();
            }

            public static final Creator<TradeAssistant> CREATOR = new Creator<TradeAssistant>() {
                @Override
                public TradeAssistant createFromParcel(Parcel source) {
                    return new TradeAssistant(source);
                }

                @Override
                public TradeAssistant[] newArray(int size) {
                    return new TradeAssistant[size];
                }
            };
        }

        public static class PreparedByBean implements Parcelable {
            @Override
            public String toString() {
                return "{" +
                        "id='" + id + '\'' +
                        '}';
            }

            /**
             * id : 7836b440393643448d4dae6e771117e2
             */

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
            }

            PreparedByBean() {
            }

            PreparedByBean(Parcel in) {
                this.id = in.readString();
            }

            public static final Creator<PreparedByBean> CREATOR = new Creator<PreparedByBean>() {
                @Override
                public PreparedByBean createFromParcel(Parcel source) {
                    return new PreparedByBean(source);
                }

                @Override
                public PreparedByBean[] newArray(int size) {
                    return new PreparedByBean[size];
                }
            };
        }

        public static class RetailPayBean implements Parcelable {
            private String orderNo;
            private String traceNo;
            private String terminalNo;
            private String payMoney;
            private String changeMoney;
            private String shroffMethod;

            public String getShroffMethod() {
                return shroffMethod;
            }

            public void setShroffMethod(String shroffMethod) {
                this.shroffMethod = shroffMethod;
            }

            public void setTraceNo(String traceNo) {
                this.traceNo = traceNo;
            }

            public String getPayMoney() {
                return payMoney;
            }

            public void setPayMoney(String payMoney) {
                this.payMoney = payMoney;
            }

            public String getChangeMoney() {
                return changeMoney;
            }

            public void setChangeMoney(String changeMoney) {
                this.changeMoney = changeMoney;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getTraceNo() {
                return traceNo;
            }

            public void setTraceN(String traceNo) {
                this.traceNo = traceNo;
            }

            public String getTerminalNo() {
                return terminalNo;
            }

            public void setTerminalNo(String terminalNo) {
                this.terminalNo = terminalNo;
            }

            public String getTransNo() {
                return transNo;
            }

            public void setTransNo(String transNo) {
                this.transNo = transNo;
            }

            private String transNo;

            public RetailPayBean() {
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.orderNo);
                dest.writeString(this.traceNo);
                dest.writeString(this.terminalNo);
                dest.writeString(this.payMoney);
                dest.writeString(this.changeMoney);
                dest.writeString(this.shroffMethod);
                dest.writeString(this.transNo);
            }

            protected RetailPayBean(Parcel in) {
                this.orderNo = in.readString();
                this.traceNo = in.readString();
                this.terminalNo = in.readString();
                this.payMoney = in.readString();
                this.changeMoney = in.readString();
                this.shroffMethod = in.readString();
                this.transNo = in.readString();
            }

            public static final Creator<RetailPayBean> CREATOR = new Creator<RetailPayBean>() {
                @Override
                public RetailPayBean createFromParcel(Parcel source) {
                    return new RetailPayBean(source);
                }

                @Override
                public RetailPayBean[] newArray(int size) {
                    return new RetailPayBean[size];
                }
            };
        }

        public static class RetailDiscountsInfoListBean implements Parcelable {
            @Override
            public String toString() {
                return "RetailDiscountsInfoListBean{" +
                        "id='" + id + '\'' +
                        ", discountsName='" + discountsName + '\'' +
                        ", discountsAmount='" + discountsAmount + '\'' +
                        ", discountsId='" + discountsId + '\'' +
                        ", discountsType='" + discountsType + '\'' +
                        '}';
            }

            private String id;
            private String discountsName;
            private String discountsAmount;
            private String discountsId;
            private String discountsType;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDiscountsName() {
                return discountsName;
            }

            public void setDiscountsName(String discountsName) {
                this.discountsName = discountsName;
            }

            public String getDiscountsAmount() {
                return discountsAmount;
            }

            public void setDiscountsAmount(String discountsAmount) {
                this.discountsAmount = discountsAmount;
            }

            public String getDiscountsId() {
                return discountsId;
            }

            public void setDiscountsId(String discountsId) {
                this.discountsId = discountsId;
            }

            public String getDiscountsType() {
                return discountsType;
            }

            public void setDiscountsType(String discountsType) {
                this.discountsType = discountsType;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.discountsName);
                dest.writeString(this.discountsAmount);
                dest.writeString(this.discountsId);
                dest.writeString(this.discountsType);
            }

            RetailDiscountsInfoListBean() {
            }

            RetailDiscountsInfoListBean(Parcel in) {
                this.id = in.readString();
                this.discountsName = in.readString();
                this.discountsAmount = in.readString();
                this.discountsId = in.readString();
                this.discountsType = in.readString();
            }

            public static final Creator<RetailDiscountsInfoListBean> CREATOR = new Creator<RetailDiscountsInfoListBean>() {
                @Override
                public RetailDiscountsInfoListBean createFromParcel(Parcel source) {
                    return new RetailDiscountsInfoListBean(source);
                }

                @Override
                public RetailDiscountsInfoListBean[] newArray(int size) {
                    return new RetailDiscountsInfoListBean[size];
                }
            };
        }

        public static class RetailOrderInfoListBean implements Parcelable {
            @Override
            public String toString() {
                return "{" +
                        ", activityFlag='" + activityFlag + '\'' +
                        ", productNum='" + productNum + '\'' +
                        ", productPrice='" + productPrice + '\'' +
                        ", productName='" + productName + '\'' +
                        ", productAmount='" + productAmount + '\'' +
                        '}';
            }

            /**
             * id : dd939c8c2af748dd9476eb3bfa0eb9f7
             * activityFlag :
             * productSellingPrice : 5000.0
//             * imei :
             * imeiManage : 0
             * productNum : 1
             * productMemberPrice :
             * barCode : 666
             * productPrice : 5000.0
             * productName : 接口测试商品串号
             * productAmount : 5000.0
             * productId : b12191b9a5624b27b98c7bd14d54eb63
             */

            private String imei;

            public String getImei() {
                return imei;
            }

            public void setImei(String imei) {
                this.imei = imei;
            }

            private String activityFlag;
            private String productNum;
            private String productPrice;
            private String productName;
            private String productAmount;

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


            public String getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(String productPrice) {
                this.productPrice = productPrice;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductAmount() {
                return productAmount;
            }

            public void setProductAmount(String productAmount) {
                this.productAmount = productAmount;
            }


            public RetailOrderInfoListBean() {
            }

            public RetailOrderInfoListBean(String productNum, String productPrice, String productName, String productAmount) {
                this.productAmount = productAmount;
                this.productNum = productNum;
                this.productPrice = productPrice;
                this.productName = productName;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.imei);
                dest.writeString(this.activityFlag);
                dest.writeString(this.productNum);
                dest.writeString(this.productPrice);
                dest.writeString(this.productName);
                dest.writeString(this.productAmount);
            }

            protected RetailOrderInfoListBean(Parcel in) {
                this.imei = in.readString();
                this.activityFlag = in.readString();
                this.productNum = in.readString();
                this.productPrice = in.readString();
                this.productName = in.readString();
                this.productAmount = in.readString();
            }

            public static final Creator<RetailOrderInfoListBean> CREATOR = new Creator<RetailOrderInfoListBean>() {
                @Override
                public RetailOrderInfoListBean createFromParcel(Parcel source) {
                    return new RetailOrderInfoListBean(source);
                }

                @Override
                public RetailOrderInfoListBean[] newArray(int size) {
                    return new RetailOrderInfoListBean[size];
                }
            };
        }


        public ResultBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.payableAmount);
            dest.writeParcelable(this.warehouseInfo, flags);
            dest.writeParcelable(this.member, flags);
            dest.writeParcelable(this.tradeAssistant, flags);
            dest.writeString(this.billDate);
            dest.writeString(this.discountsAmount);
            dest.writeString(this.proceedsAmount);
            dest.writeString(this.endpayAmount);
            dest.writeString(this.remarks);
            dest.writeString(this.companycode);
            dest.writeParcelable(this.preparedBy, flags);
            dest.writeString(this.id);
            dest.writeString(this.productNum);
            dest.writeString(this.vchcode);
            dest.writeString(this.orderStatue);
            dest.writeString(this.createDate);
            dest.writeString(this.productAmount);
            dest.writeString(this.otherAmount);
            dest.writeString(this.sjskAmount);
            dest.writeTypedList(this.retailOrderInfoList);
            dest.writeTypedList(this.retailDiscountsInfoList);
            dest.writeTypedList(this.retailPays);
        }

        protected ResultBean(Parcel in) {
            this.payableAmount = in.readString();
            this.warehouseInfo = in.readParcelable(WarehouseInfoBean.class.getClassLoader());
            this.member = in.readParcelable(MemberBean.class.getClassLoader());
            this.tradeAssistant = in.readParcelable(TradeAssistant.class.getClassLoader());
            this.billDate = in.readString();
            this.discountsAmount = in.readString();
            this.proceedsAmount = in.readString();
            this.endpayAmount = in.readString();
            this.remarks = in.readString();
            this.companycode = in.readString();
            this.preparedBy = in.readParcelable(PreparedByBean.class.getClassLoader());
            this.id = in.readString();
            this.productNum = in.readString();
            this.vchcode = in.readString();
            this.orderStatue = in.readString();
            this.createDate = in.readString();
            this.productAmount = in.readString();
            this.otherAmount = in.readString();
            this.sjskAmount = in.readString();
            this.retailOrderInfoList = in.createTypedArrayList(RetailOrderInfoListBean.CREATOR);
            this.retailDiscountsInfoList = in.createTypedArrayList(RetailDiscountsInfoListBean.CREATOR);
            this.retailPays = in.createTypedArrayList(RetailPayBean.CREATOR);
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel source) {
                return new ResultBean(source);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.result, flags);
        dest.writeString(this.flag);
        dest.writeString(this.msg);
    }


    private SubmitOrderBean(Parcel in) {
        this.result = in.readParcelable(ResultBean.class.getClassLoader());
        this.flag = in.readString();
        this.msg = in.readString();
    }

    public static final Creator<SubmitOrderBean> CREATOR = new Creator<SubmitOrderBean>() {
        @Override
        public SubmitOrderBean createFromParcel(Parcel source) {
            return new SubmitOrderBean(source);
        }

        @Override
        public SubmitOrderBean[] newArray(int size) {
            return new SubmitOrderBean[size];
        }
    };


}
