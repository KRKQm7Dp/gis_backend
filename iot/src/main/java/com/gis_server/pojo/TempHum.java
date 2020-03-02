package com.gis_server.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TempHum {
    private Integer id;

    private Double temp;

    private Double humidity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    private Integer deviceId;

    public TempHum(Integer id, Double temp, Double humidity, Date time, Integer deviceId) {
        this.id = id;
        this.temp = temp;
        this.humidity = humidity;
        this.time = time;
        this.deviceId = deviceId;
    }

    public TempHum() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
