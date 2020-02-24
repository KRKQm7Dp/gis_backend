package com.gis_server.pojo;

public class SysRolePermission {
    private Integer rid;

    private Integer pid;

    public SysRolePermission(Integer rid, Integer pid) {
        this.rid = rid;
        this.pid = pid;
    }

    public SysRolePermission() {
        super();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
