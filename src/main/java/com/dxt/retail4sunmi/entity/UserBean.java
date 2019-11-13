package com.dxt.retail4sunmi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu04 on 2017/12/13.
 */

public class UserBean implements Parcelable {

    /**
     * officeId : 3aa6077aebd211e7bb7b005056a82e70
     * users : [{"id":"3aa607f2ebd211e7bb7b005056a82e70","name":"m"},{"id":"a297d90bb8664300a98b1307c531ec2e","name":"m1"},{"id":"fdb46610efd64385a4dc0d9f805025bd","name":"bb"}]
     * ktypeName : 宇宙仓库
     * companyName : 测试公司
     * msg :
     * companycode : 974270
     * userLogFlag : 1
     * flag : success
     * comShroffAccountlist : [{"id":"bd80c58aaf7e46b0ba2c3dc9af5e466d","shroffMethod":"支付宝人脸支付","shroffAccount":"","appid":"","shroffType":"1000"},{"id":"6a559c7cb3d541d1887abac5cd9e8f81","shroffMethod":"微信扫码支付","appid":"wxe8203934210912b3","mchId":"1510677101","shroffType":"8","subMchId":"1512267851"},{"id":"9bc9a8c27d7546e7a476f4d9d45246f9","shroffMethod":"支付宝","shroffType":"3"},{"id":"0de1c569c02443209e3217d979abb7b4","shroffMethod":"测试收款","shroffType":"111"},{"id":"d7e5b4bdeb4147f9b63394fefccfac09","shroffMethod":"火星收款","shroffType":"90809"},{"id":"ce3580107b3e4905ba087bfc0932fed3","shroffMethod":"微信当面付","appid":"wx31b3b73f2cc6cd4f","mchId":"1280195501","shroffType":"10","subMchId":"1298675101"},{"id":"65bef8f0caf241d0b889ac8cc9db2891","shroffMethod":"支付宝当面付","shroffAccount":"2088611528727632","appid":"2015102600554645","shroffType":"9"},{"id":"794c0e19917c4b3d8d333af9123abb1b","shroffMethod":"微信","shroffType":"2"},{"id":"afc6276cc904403cb434f0d9b27711ec","shroffMethod":"银行卡","shroffType":"5"},{"id":"9823ad0e783d4df6933901d65155e6ba","shroffMethod":"现金","shroffAccount":"","appid":"","mchId":"","shroffType":"1","subMchId":""},{"id":"597285331c0648958b1f2f69f110a81a","shroffMethod":"POS刷卡支付","shroffAccount":"","appid":"","mchId":"","shroffType":"12","subMchId":""},{"id":"fc4e4a2927a747e6a9d2482fd3decdb2","shroffMethod":"手持pos支付","shroffAccount":"","appid":"","mchId":"","shroffType":"6","subMchId":""},{"id":"5d421ababe5b42528dba2a96031aa491","shroffMethod":"支付宝扫码支付","shroffAccount":"2088611528727632","appid":"2015102600554645","shroffType":"7"}]
     * memberPropertyList : [{"id":"8e0b5c4751e8427594a2694277b542e2","enName":"NAME","name":"姓名","columnName":"NAME","valueType":"1","fieldName":"name","required":"1","type":"1"},{"id":"3049fb880d01476a88eb0ca1a83375f6","enName":"MOBILE","name":"手机号","columnName":"MOBILE","valueType":"1","fieldName":"mobile","required":"1","type":"1"},{"id":"fac26fa27f8e4175847dd6da04ea9dc1","enName":"DIY_TEXT_PROPERTY1","name":"身份证号","columnName":"DIY_TEXT_PROPERTY1","valueType":"1","fieldName":"diyTextProperty1","required":"0","type":"1"},{"id":"46770fb9415f474c8e81bdedf04b4aa3","enName":"AGE","name":"年龄","columnName":"AGE","valueType":"1","fieldName":"age","required":"1","type":"1"},{"id":"d1ef2beb27e446a4bbe7ca3d7493e9de","enName":"SEX","name":"性别","columnName":"SEX","valueType":"1","fieldName":"sex","required":"1","type":"1"},{"id":"b80c6e722a8449118aeec2f91fa5fe70","enName":"","name":"附加","columnName":"","valueType":"1","fieldName":"","required":"0","type":"2"}]
     * officeName : 测试公司
     * userId : 3aa607f2ebd211e7bb7b005056a82e70
     * warehouseInfoList : [{"id":"6954938133414799a753fcc3ac41f303","warehouseName":"火星仓库"},{"id":"e0924abcac294f4b8c8fcc76cc24cc88","warehouseName":"看了看大家了"},{"id":"cf1303dc5ce6430dbfbb8340e9c368fd","warehouseName":"模板仓库"},{"id":"5137bd3b33c24a1b97109710c43b3090","warehouseName":"宇宙仓库"},{"id":"2cd58da9d0a34138baf60ec36e960999","warehouseName":"地球仓库"},{"id":"e899d9e004a841199c80a82d4fbe2b1f","warehouseName":"冥王库库"}]
     * userName : m
     * ktypeid : 5137bd3b33c24a1b97109710c43b3090
     * storeId : 123123123
     */

    private String officeId;
    private String ktypeName;
    private String companyName;
    private String msg;
    private String companycode;
    private String userLogFlag;
    private String flag;
    private String officeName;
    private String userId;
    private String userName;
    private String ktypeid;
    private String storeId;
    private List<UsersBean> users;
    private List<ComShroffAccountlistBean> comShroffAccountlist;
    private List<MemberPropertyListBean> memberPropertyList;
    private List<WarehouseInfoListBean> warehouseInfoList;

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getKtypeName() {
        return ktypeName;
    }

    public void setKtypeName(String ktypeName) {
        this.ktypeName = ktypeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getUserLogFlag() {
        return userLogFlag;
    }

    public void setUserLogFlag(String userLogFlag) {
        this.userLogFlag = userLogFlag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKtypeid() {
        return ktypeid;
    }

    public void setKtypeid(String ktypeid) {
        this.ktypeid = ktypeid;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public List<ComShroffAccountlistBean> getComShroffAccountlist() {
        return comShroffAccountlist;
    }

    public void setComShroffAccountlist(List<ComShroffAccountlistBean> comShroffAccountlist) {
        this.comShroffAccountlist = comShroffAccountlist;
    }

    public List<MemberPropertyListBean> getMemberPropertyList() {
        return memberPropertyList;
    }

    public void setMemberPropertyList(List<MemberPropertyListBean> memberPropertyList) {
        this.memberPropertyList = memberPropertyList;
    }

    public List<WarehouseInfoListBean> getWarehouseInfoList() {
        return warehouseInfoList;
    }

    public void setWarehouseInfoList(List<WarehouseInfoListBean> warehouseInfoList) {
        this.warehouseInfoList = warehouseInfoList;
    }

    public static class UsersBean implements Parcelable {
        /**
         * id : 3aa607f2ebd211e7bb7b005056a82e70
         * name : m
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
        }

        public UsersBean() {
        }

        protected UsersBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
        }

        public static final Creator<UsersBean> CREATOR = new Creator<UsersBean>() {
            @Override
            public UsersBean createFromParcel(Parcel source) {
                return new UsersBean(source);
            }

            @Override
            public UsersBean[] newArray(int size) {
                return new UsersBean[size];
            }
        };
    }

    public static class ComShroffAccountlistBean implements Parcelable {
        /**
         * id : bd80c58aaf7e46b0ba2c3dc9af5e466d
         * shroffMethod : 支付宝人脸支付
         * shroffAccount :
         * appid :
         * shroffType : 1000
         * mchId : 1510677101
         * subMchId : 1512267851
         */

        private String id;
        private String shroffMethod;
        private String shroffAccount;
        private String appid;
        private String shroffType;
        private String mchId;
        private String subMchId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShroffMethod() {
            return shroffMethod;
        }

        public void setShroffMethod(String shroffMethod) {
            this.shroffMethod = shroffMethod;
        }

        public String getShroffAccount() {
            return shroffAccount;
        }

        public void setShroffAccount(String shroffAccount) {
            this.shroffAccount = shroffAccount;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getShroffType() {
            return shroffType;
        }

        public void setShroffType(String shroffType) {
            this.shroffType = shroffType;
        }

        public String getMchId() {
            return mchId;
        }

        public void setMchId(String mchId) {
            this.mchId = mchId;
        }

        public String getSubMchId() {
            return subMchId;
        }

        public void setSubMchId(String subMchId) {
            this.subMchId = subMchId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.shroffMethod);
            dest.writeString(this.shroffAccount);
            dest.writeString(this.appid);
            dest.writeString(this.shroffType);
            dest.writeString(this.mchId);
            dest.writeString(this.subMchId);
        }

        public ComShroffAccountlistBean() {
        }

        protected ComShroffAccountlistBean(Parcel in) {
            this.id = in.readString();
            this.shroffMethod = in.readString();
            this.shroffAccount = in.readString();
            this.appid = in.readString();
            this.shroffType = in.readString();
            this.mchId = in.readString();
            this.subMchId = in.readString();
        }

        public static final Creator<ComShroffAccountlistBean> CREATOR = new Creator<ComShroffAccountlistBean>() {
            @Override
            public ComShroffAccountlistBean createFromParcel(Parcel source) {
                return new ComShroffAccountlistBean(source);
            }

            @Override
            public ComShroffAccountlistBean[] newArray(int size) {
                return new ComShroffAccountlistBean[size];
            }
        };
    }

    public static class MemberPropertyListBean implements Parcelable {
        /**
         * id : 8e0b5c4751e8427594a2694277b542e2
         * enName : NAME
         * name : 姓名
         * columnName : NAME
         * valueType : 1
         * fieldName : name
         * required : 1
         * type : 1
         */

        private String id;
        private String enName;
        private String name;
        private String columnName;
        private String valueType;
        private String fieldName;
        private String required;
        private String type;
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getValueType() {
            return valueType;
        }

        public void setValueType(String valueType) {
            this.valueType = valueType;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getRequired() {
            return required;
        }

        public void setRequired(String required) {
            this.required = required;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.enName);
            dest.writeString(this.name);
            dest.writeString(this.columnName);
            dest.writeString(this.valueType);
            dest.writeString(this.fieldName);
            dest.writeString(this.required);
            dest.writeString(this.type);
            dest.writeString(this.value);
        }

        public MemberPropertyListBean() {
        }

        protected MemberPropertyListBean(Parcel in) {
            this.id = in.readString();
            this.enName = in.readString();
            this.name = in.readString();
            this.columnName = in.readString();
            this.valueType = in.readString();
            this.fieldName = in.readString();
            this.required = in.readString();
            this.type = in.readString();
            this.value = in.readString();
        }

        public static final Creator<MemberPropertyListBean> CREATOR = new Creator<MemberPropertyListBean>() {
            @Override
            public MemberPropertyListBean createFromParcel(Parcel source) {
                return new MemberPropertyListBean(source);
            }

            @Override
            public MemberPropertyListBean[] newArray(int size) {
                return new MemberPropertyListBean[size];
            }
        };
    }

    public static class WarehouseInfoListBean implements Parcelable {
        /**
         * id : 6954938133414799a753fcc3ac41f303
         * warehouseName : 火星仓库
         */

        private String id;
        private String warehouseName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.warehouseName);
        }

        public WarehouseInfoListBean() {
        }

        protected WarehouseInfoListBean(Parcel in) {
            this.id = in.readString();
            this.warehouseName = in.readString();
        }

        public static final Creator<WarehouseInfoListBean> CREATOR = new Creator<WarehouseInfoListBean>() {
            @Override
            public WarehouseInfoListBean createFromParcel(Parcel source) {
                return new WarehouseInfoListBean(source);
            }

            @Override
            public WarehouseInfoListBean[] newArray(int size) {
                return new WarehouseInfoListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.officeId);
        dest.writeString(this.ktypeName);
        dest.writeString(this.companyName);
        dest.writeString(this.msg);
        dest.writeString(this.companycode);
        dest.writeString(this.userLogFlag);
        dest.writeString(this.flag);
        dest.writeString(this.officeName);
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.ktypeid);
        dest.writeString(this.storeId);
        dest.writeList(this.users);
        dest.writeList(this.comShroffAccountlist);
        dest.writeList(this.memberPropertyList);
        dest.writeList(this.warehouseInfoList);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.officeId = in.readString();
        this.ktypeName = in.readString();
        this.companyName = in.readString();
        this.msg = in.readString();
        this.companycode = in.readString();
        this.userLogFlag = in.readString();
        this.flag = in.readString();
        this.officeName = in.readString();
        this.userId = in.readString();
        this.userName = in.readString();
        this.ktypeid = in.readString();
        this.storeId = in.readString();
        this.users = new ArrayList<UsersBean>();
        in.readList(this.users, UsersBean.class.getClassLoader());
        this.comShroffAccountlist = new ArrayList<ComShroffAccountlistBean>();
        in.readList(this.comShroffAccountlist, ComShroffAccountlistBean.class.getClassLoader());
        this.memberPropertyList = new ArrayList<MemberPropertyListBean>();
        in.readList(this.memberPropertyList, MemberPropertyListBean.class.getClassLoader());
        this.warehouseInfoList = new ArrayList<WarehouseInfoListBean>();
        in.readList(this.warehouseInfoList, WarehouseInfoListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
