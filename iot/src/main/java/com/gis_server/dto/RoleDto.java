package com.gis_server.dto;

import com.gis_server.pojo.SysRole;

import java.util.List;

public class RoleDto extends SysRole {

    private List<Integer> permissionIds;

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
