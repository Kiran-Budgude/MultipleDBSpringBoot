package com.example.two.db.demo.notificationFCM;

import com.example.two.db.demo.entity.db2.GenericNotification;

import java.sql.Timestamp;
import java.util.List;

public class PushNotificationDto extends GenericNotification {

    private String deviceKeys;

    private Timestamp sendDate;

    private Long customerId;

    private String city;

    public String getDeviceKeys() {
        return deviceKeys;
    }

    public void setDeviceKeys(String deviceKeys) {
        this.deviceKeys = deviceKeys;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
