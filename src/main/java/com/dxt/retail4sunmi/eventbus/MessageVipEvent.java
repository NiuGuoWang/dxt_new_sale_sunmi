package com.dxt.retail4sunmi.eventbus;

/**
 * Created by liu04 on 2017/9/20.
 */

public class MessageVipEvent {
    private String phone;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MessageVipEvent(String phone, String id) {
        this.phone = phone;
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
