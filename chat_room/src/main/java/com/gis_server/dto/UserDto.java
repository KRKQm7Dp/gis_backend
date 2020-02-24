package com.gis_server.dto;

import com.gis_server.pojo.SysUser;

import java.util.Date;

public class UserDto extends SysUser {

    private Integer fFriendgroupsid;

    public Integer getfFriendgroupsid() {
        return fFriendgroupsid;
    }

    public void setfFriendgroupsid(Integer fFriendgroupsid) {
        this.fFriendgroupsid = fFriendgroupsid;
    }

    public UserDto(Integer uId, String uLoginid, String uNickname, String uPassword, String uSignature, Boolean uSex, Date uBirthday, String uTelephone, String uName, String uEmail, String uIntro, String uHeadportrait, String uShengxiao, Integer uAge, String uConstellation, String uBloodtype, String uSchooltag, String uVocation, Integer uNationid, Integer uProvinceid, Integer uCityid, Integer uFriendshippolicyid, Integer uUserstateid, String uFriendpolicyquestion, String uFriendpolicyanswer, String uFriendpolicypassword, Boolean enabled, Boolean notExpired, Boolean accountNotLocked, Boolean credentialsNotExpired, Date createTime, Date updateTime, Integer createUser, Integer updateUser, Date lastLoginTime, Integer fFriendgroupsid) {
        super(uId, uLoginid, uNickname, uPassword, uSignature, uSex, uBirthday, uTelephone, uName, uEmail, uIntro, uHeadportrait, uShengxiao, uAge, uConstellation, uBloodtype, uSchooltag, uVocation, uNationid, uProvinceid, uCityid, uFriendshippolicyid, uUserstateid, uFriendpolicyquestion, uFriendpolicyanswer, uFriendpolicypassword, enabled, notExpired, accountNotLocked, credentialsNotExpired, createTime, updateTime, createUser, updateUser, lastLoginTime);
        this.fFriendgroupsid = fFriendgroupsid;
    }
}
