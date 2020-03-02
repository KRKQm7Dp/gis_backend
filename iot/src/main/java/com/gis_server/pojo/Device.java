package com.gis_server.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Device {

    private Integer id;

    private String name;

    private String describe;

    private Boolean status;

    private Double positionX;

    private Double positionY;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date connTime;

    private Integer userId;

    public Device(Integer id, String name, String describe, Boolean status, Double positionX, Double positionY, Date connTime, Integer userId) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.status = status;
        this.positionX = positionX;
        this.positionY = positionY;
        this.connTime = connTime;
        this.userId = userId;
    }

    public Device() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getPositionX() {
        return positionX;
    }

    public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }

    public Double getPositionY() {
        return positionY;
    }

    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }

    public Date getConnTime() {
        return connTime;
    }

    public void setConnTime(Date connTime) {
        this.connTime = connTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
