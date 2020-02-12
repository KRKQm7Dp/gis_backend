package com.gis_server.pojo;

public class SysPermission {
    private Integer id;

    private String permissionName;

    private String permissionUrl;

    private Integer parentId;

    public SysPermission(Integer id, String permissionName, String permissionUrl, Integer parentId) {
        this.id = id;
        this.permissionName = permissionName;
        this.permissionUrl = permissionUrl;
        this.parentId = parentId;
    }

    public SysPermission() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl == null ? null : permissionUrl.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", permissionUrl='" + permissionUrl + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
