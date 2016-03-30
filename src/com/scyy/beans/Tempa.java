package com.scyy.beans;

/**
 * Created by LYH on 2016-03-24.
 * 说明：冷链温度实体类
 */

import java.util.Date;

public class Tempa {

    //温度1
    private double temp1;
    //温度2
    private double temp2;
    //gps_time
    private Date collectTime = new Date();
    //entry_time
    private Date entryTime = new Date();
    //车牌号
    private String carNo;

    public double getTemp1() {
        return temp1;
    }

    public void setTemp1(double temp1) {
        this.temp1 = temp1;
    }

    public double getTemp2() {
        return temp2;
    }

    public void setTemp2(double temp2) {
        this.temp2 = temp2;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
}
