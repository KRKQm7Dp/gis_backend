package com.gis_server.pojo;

public class SysUserRole {
    private Integer uid;

    private Integer rid;

    public SysUserRole(Integer uid, Integer rid) {
        this.uid = uid;
        this.rid = rid;
    }

    public SysUserRole() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
