package com.gis_server.dto;

import com.gis_server.pojo.SysMeta;
import com.gis_server.pojo.SysPermission;

import java.util.List;

public class PermissionDto extends SysPermission {

    private SysMeta meta;

    private List<PermissionDto> children;

    public SysMeta getMeta() {
        return meta;
    }

    public void setMeta(SysMeta meta) {
        this.meta = meta;
    }

    public List<PermissionDto> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PermissionDto{" +
                "meta=" + meta +
                ", children=" + children +
                '}';
    }
}
