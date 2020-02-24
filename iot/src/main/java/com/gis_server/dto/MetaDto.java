package com.gis_server.dto;

import com.gis_server.pojo.SysMeta;

import java.util.List;

public class MetaDto extends SysMeta {

    List<RoleDto> roles;

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
