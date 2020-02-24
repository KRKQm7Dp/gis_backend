package com.gis_server.pojo;

public class SysPermission {

    private Integer id;

    private String path;

    private String component;

    private String redirect;

    private String name;

    private Integer metaId;

    private String childrensid;

    private Boolean hidden;

    private Integer parentId;

    public SysPermission(Integer id, String path, String component, String redirect, String name, Integer metaId, String childrensid, Boolean hidden, Integer parentId) {
        this.id = id;
        this.path = path;
        this.component = component;
        this.redirect = redirect;
        this.name = name;
        this.metaId = metaId;
        this.childrensid = childrensid;
        this.hidden = hidden;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect == null ? null : redirect.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMetaId() {
        return metaId;
    }

    public void setMetaId(Integer metaId) {
        this.metaId = metaId;
    }

    public String getChildrensid() {
        return childrensid;
    }

    public void setChildrensid(String childrensid) {
        this.childrensid = childrensid == null ? null : childrensid.trim();
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
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
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", redirect='" + redirect + '\'' +
                ", name='" + name + '\'' +
                ", metaId=" + metaId +
                ", childrensid='" + childrensid + '\'' +
                ", hidden=" + hidden +
                ", parentId=" + parentId +
                '}';
    }
}
