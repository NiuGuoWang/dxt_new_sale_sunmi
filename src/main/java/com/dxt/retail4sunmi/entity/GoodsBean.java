package com.dxt.retail4sunmi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * liu04 on 2017/12/16.
 */

public class GoodsBean implements Parcelable {
    /**
     * id : 9a09d13e23634ccfb80bb69252a06635
     * retailPrice : 3000.0
     * name : 华为荣耀
     * imeiManage : 1
     * barCode : HWRY
     */

    private String id;
    private String unitType;
    private boolean unitFirst;

    public boolean getUnitFirst() {
        return unitFirst;
    }

    public void setUnitFirst(boolean unitFirst) {
        this.unitFirst = unitFirst;
    }

    private String batchManage;
    private String batchNo;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchManage() {
        return batchManage;
    }

    public void setBatchManage(String batchManage) {
        this.batchManage = batchManage;
    }


    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public ProductMultipleUnit getProductMultipleUnit() {
        return productMultipleUnit;
    }

    public void setProductMultipleUnit(ProductMultipleUnit productMultipleUnit) {
        this.productMultipleUnit = productMultipleUnit;
    }

    private ProductUnit productUnit;
    private ProductMultipleUnit productMultipleUnit;

    public ProductUnit getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(ProductUnit productUnit) {
        this.productUnit = productUnit;
    }

    private String name;

    public void setMemberPrice(String memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    private String imeiManage;
    private String barCode;
    private double num = 1;
    private String priceType = "";
    private String memberPrice;
    private double retailPrice;//标价
    private String realPrice;//实际价格
    private String kcnum;//库存

    @Override
    public String toString() {
        return "GoodsBean{" +
                "id='" + id + '\'' +
                ", unitType='" + unitType + '\'' +
                ", productUnit=" + productUnit +
                ", productMultipleUnit=" + productMultipleUnit +
                ", name='" + name + '\'' +
                ", imeiManage='" + imeiManage + '\'' +
                ", barCode='" + barCode + '\'' +
                ", num=" + num +
                ", priceType='" + priceType + '\'' +
                ", memberPrice='" + memberPrice + '\'' +
                ", retailPrice=" + retailPrice +
                ", realPrice='" + realPrice + '\'' +
                ", imei='" + imei + '\'' +
                ", kcnum='" + kcnum + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GoodsBean bean = (GoodsBean) o;

        return id != null ? id.equals(bean.id) : bean.id == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }


    public String getMemberPrice() {
        return memberPrice;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    private String imei;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
    public String getKcnum() {
        return kcnum;
    }

    public void setKcnum(String kcnum) {
        this.kcnum = kcnum;
    }
    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImeiManage() {
        return imeiManage;
    }

    public void setImeiManage(String imeiManage) {
        this.imeiManage = imeiManage;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public GoodsBean() {
    }

    public static class ProductUnit implements Parcelable {
        private String id;

        ProductUnit(Parcel in) {
            id = in.readString();
        }

        public final static Creator<ProductUnit> CREATOR = new Creator<ProductUnit>() {
            @Override
            public ProductUnit createFromParcel(Parcel in) {
                return new ProductUnit(in);
            }

            @Override
            public ProductUnit[] newArray(int size) {
                return new ProductUnit[size];
            }
        };

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
            dest.writeString(id);
        }
    }

    public static class ProductMultipleUnit implements Parcelable {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<ProductSubUnit> getProductSubUnitList() {
            return productSubUnitList;
        }

        public void setProductSubUnitList(List<ProductSubUnit> productSubUnitList) {
            this.productSubUnitList = productSubUnitList;
        }

        private List<ProductSubUnit> productSubUnitList;
        private int selectProductUnitNum;

        public int getSelectProductUnitNum() {
            return selectProductUnitNum;
        }

        public void setSelectProductUnitNum(int selectProductUnitNum) {
            this.selectProductUnitNum = selectProductUnitNum;
        }

        public static class ProductSubUnit implements Parcelable {
            private String id;
            private String name;
            private String count;
            private String mainUnitFlag;
            private ProductUnitPrice productUnitPrice;

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

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getMainUnitFlag() {
                return mainUnitFlag;
            }

            public void setMainUnitFlag(String mainUnitFlag) {
                this.mainUnitFlag = mainUnitFlag;
            }

            public ProductUnitPrice getProductUnitPrice() {
                return productUnitPrice;
            }

            public void setProductUnitPrice(ProductUnitPrice productUnitPrice) {
                this.productUnitPrice = productUnitPrice;
            }

            public static class ProductUnitPrice implements Parcelable {
                private String price;

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.price);
                }

                ProductUnitPrice() {
                }

                ProductUnitPrice(Parcel in) {
                    this.price = in.readString();
                }

                public static final Creator<ProductUnitPrice> CREATOR = new Creator<ProductUnitPrice>() {
                    @Override
                    public ProductUnitPrice createFromParcel(Parcel source) {
                        return new ProductUnitPrice(source);
                    }

                    @Override
                    public ProductUnitPrice[] newArray(int size) {
                        return new ProductUnitPrice[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.name);
                dest.writeString(this.count);
                dest.writeString(this.mainUnitFlag);
                dest.writeParcelable(this.productUnitPrice, flags);
            }

            public ProductSubUnit() {
            }

            ProductSubUnit(Parcel in) {
                this.id = in.readString();
                this.name = in.readString();
                this.count = in.readString();
                this.mainUnitFlag = in.readString();
                this.productUnitPrice = in.readParcelable(ProductUnitPrice.class.getClassLoader());
            }

            public static final Creator<ProductSubUnit> CREATOR = new Creator<ProductSubUnit>() {
                @Override
                public ProductSubUnit createFromParcel(Parcel source) {
                    return new ProductSubUnit(source);
                }

                @Override
                public ProductSubUnit[] newArray(int size) {
                    return new ProductSubUnit[size];
                }
            };
        }

        ProductMultipleUnit() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeTypedList(this.productSubUnitList);
            dest.writeInt(this.selectProductUnitNum);
        }

        ProductMultipleUnit(Parcel in) {
            this.id = in.readString();
            this.productSubUnitList = in.createTypedArrayList(ProductSubUnit.CREATOR);
            this.selectProductUnitNum = in.readInt();
        }

        public static final Creator<ProductMultipleUnit> CREATOR = new Creator<ProductMultipleUnit>() {
            @Override
            public ProductMultipleUnit createFromParcel(Parcel source) {
                return new ProductMultipleUnit(source);
            }

            @Override
            public ProductMultipleUnit[] newArray(int size) {
                return new ProductMultipleUnit[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.unitType);
        dest.writeByte(this.unitFirst ? (byte) 1 : (byte) 0);
        dest.writeString(this.batchManage);
        dest.writeString(this.batchNo);
        dest.writeParcelable(this.productUnit, flags);
        dest.writeParcelable(this.productMultipleUnit, flags);
        dest.writeString(this.name);
        dest.writeString(this.imeiManage);
        dest.writeString(this.barCode);
        dest.writeDouble(this.num);
        dest.writeString(this.priceType);
        dest.writeString(this.memberPrice);
        dest.writeDouble(this.retailPrice);
        dest.writeString(this.realPrice);
        dest.writeString(this.imei);
        dest.writeString(this.kcnum);
    }

    protected GoodsBean(Parcel in) {
        this.id = in.readString();
        this.unitType = in.readString();
        this.unitFirst = in.readByte() != 0;
        this.batchManage = in.readString();
        this.batchNo = in.readString();
        this.productUnit = in.readParcelable(ProductUnit.class.getClassLoader());
        this.productMultipleUnit = in.readParcelable(ProductMultipleUnit.class.getClassLoader());
        this.name = in.readString();
        this.imeiManage = in.readString();
        this.barCode = in.readString();
        this.num = in.readDouble();
        this.priceType = in.readString();
        this.memberPrice = in.readString();
        this.retailPrice = in.readDouble();
        this.realPrice = in.readString();
        this.imei = in.readString();
        this.kcnum = in.readString();
    }

    public static final Creator<GoodsBean> CREATOR = new Creator<GoodsBean>() {
        @Override
        public GoodsBean createFromParcel(Parcel source) {
            return new GoodsBean(source);
        }

        @Override
        public GoodsBean[] newArray(int size) {
            return new GoodsBean[size];
        }
    };
}
