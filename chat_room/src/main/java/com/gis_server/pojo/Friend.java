package com.gis_server.pojo;

public class Friend {
    private Integer fId;

    private String fFirendid;

    private String fUserid;

    private String fName;

    private Integer fFriendtypeid;

    private Integer fFriendgroupsid;

    public Friend(Integer fId, String fFirendid, String fUserid, String fName, Integer fFriendtypeid, Integer fFriendgroupsid) {
        this.fId = fId;
        this.fFirendid = fFirendid;
        this.fUserid = fUserid;
        this.fName = fName;
        this.fFriendtypeid = fFriendtypeid;
        this.fFriendgroupsid = fFriendgroupsid;
    }

    public Friend() {
        super();
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfFirendid() {
        return fFirendid;
    }

    public void setfFirendid(String fFirendid) {
        this.fFirendid = fFirendid == null ? null : fFirendid.trim();
    }

    public String getfUserid() {
        return fUserid;
    }

    public void setfUserid(String fUserid) {
        this.fUserid = fUserid == null ? null : fUserid.trim();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public Integer getfFriendtypeid() {
        return fFriendtypeid;
    }

    public void setfFriendtypeid(Integer fFriendtypeid) {
        this.fFriendtypeid = fFriendtypeid;
    }

    public Integer getfFriendgroupsid() {
        return fFriendgroupsid;
    }

    public void setfFriendgroupsid(Integer fFriendgroupsid) {
        this.fFriendgroupsid = fFriendgroupsid;
    }
}
