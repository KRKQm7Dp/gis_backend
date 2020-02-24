package com.gis_server.pojo;

public class SysMeta {
    private Integer id;

    private String title;

    private String icon;

    public SysMeta(Integer id, String title, String icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public SysMeta() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}
