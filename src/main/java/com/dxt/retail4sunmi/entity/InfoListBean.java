package com.dxt.retail4sunmi.entity;


import com.dxt.retail4sunmi.util.Arith;

/**
 * Created by star on 2018/1/19 0019.
 */

public class InfoListBean {
    private String name;
    private String price;
    private String num;
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public InfoListBean(String name, String price) {
        this.name = name;
        this.price = price;
        num ="1.0";
        amount =String.valueOf(Arith.mul(Double.parseDouble(price),Double.parseDouble(num)));
    }

    public InfoListBean(String name, String price, String num) {
        this.name = name;
        this.price = price;
        this.num = num;
        amount =String.valueOf(Arith.mul(Double.parseDouble(price),Double.parseDouble(num)));
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "InfoListBean{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
