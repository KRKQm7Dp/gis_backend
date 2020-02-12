package com.gis_server.pojo;

import java.util.Date;

public class SysUser {
    private Integer uId;

    private String uLoginid;

    private String uNickname;

    private String uPassword;

    private String uSignature;

    private Boolean uSex;

    private Date uBirthday;

    private String uTelephone;

    private String uName;

    private String uEmail;

    private String uIntro;

    private String uHeadportrait;

    private String uShengxiao;

    private Integer uAge;

    private String uConstellation;

    private String uBloodtype;

    private String uSchooltag;

    private String uVocation;

    private Integer uNationid;

    private Integer uProvinceid;

    private Integer uCityid;

    private Integer uFriendshippolicyid;

    private Integer uUserstateid;

    private String uFriendpolicyquestion;

    private String uFriendpolicyanswer;

    private String uFriendpolicypassword;

    private Boolean enabled;

    private Boolean notExpired;

    private Boolean accountNotLocked;

    private Boolean credentialsNotExpired;

    private Date createTime;

    private Date updateTime;

    private Integer createUser;

    private Integer updateUser;

    private Date lastLoginTime;

    public SysUser(Integer uId, String uLoginid, String uNickname, String uPassword, String uSignature, Boolean uSex, Date uBirthday, String uTelephone, String uName, String uEmail, String uIntro, String uHeadportrait, String uShengxiao, Integer uAge, String uConstellation, String uBloodtype, String uSchooltag, String uVocation, Integer uNationid, Integer uProvinceid, Integer uCityid, Integer uFriendshippolicyid, Integer uUserstateid, String uFriendpolicyquestion, String uFriendpolicyanswer, String uFriendpolicypassword, Boolean enabled, Boolean notExpired, Boolean accountNotLocked, Boolean credentialsNotExpired, Date createTime, Date updateTime, Integer createUser, Integer updateUser, Date lastLoginTime) {
        this.uId = uId;
        this.uLoginid = uLoginid;
        this.uNickname = uNickname;
        this.uPassword = uPassword;
        this.uSignature = uSignature;
        this.uSex = uSex;
        this.uBirthday = uBirthday;
        this.uTelephone = uTelephone;
        this.uName = uName;
        this.uEmail = uEmail;
        this.uIntro = uIntro;
        this.uHeadportrait = uHeadportrait;
        this.uShengxiao = uShengxiao;
        this.uAge = uAge;
        this.uConstellation = uConstellation;
        this.uBloodtype = uBloodtype;
        this.uSchooltag = uSchooltag;
        this.uVocation = uVocation;
        this.uNationid = uNationid;
        this.uProvinceid = uProvinceid;
        this.uCityid = uCityid;
        this.uFriendshippolicyid = uFriendshippolicyid;
        this.uUserstateid = uUserstateid;
        this.uFriendpolicyquestion = uFriendpolicyquestion;
        this.uFriendpolicyanswer = uFriendpolicyanswer;
        this.uFriendpolicypassword = uFriendpolicypassword;
        this.enabled = enabled;
        this.notExpired = notExpired;
        this.accountNotLocked = accountNotLocked;
        this.credentialsNotExpired = credentialsNotExpired;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.lastLoginTime = lastLoginTime;
    }

    public SysUser() {
        super();
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuLoginid() {
        return uLoginid;
    }

    public void setuLoginid(String uLoginid) {
        this.uLoginid = uLoginid == null ? null : uLoginid.trim();
    }

    public String getuNickname() {
        return uNickname;
    }

    public void setuNickname(String uNickname) {
        this.uNickname = uNickname == null ? null : uNickname.trim();
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    public String getuSignature() {
        return uSignature;
    }

    public void setuSignature(String uSignature) {
        this.uSignature = uSignature == null ? null : uSignature.trim();
    }

    public Boolean getuSex() {
        return uSex;
    }

    public void setuSex(Boolean uSex) {
        this.uSex = uSex;
    }

    public Date getuBirthday() {
        return uBirthday;
    }

    public void setuBirthday(Date uBirthday) {
        this.uBirthday = uBirthday;
    }

    public String getuTelephone() {
        return uTelephone;
    }

    public void setuTelephone(String uTelephone) {
        this.uTelephone = uTelephone == null ? null : uTelephone.trim();
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail == null ? null : uEmail.trim();
    }

    public String getuIntro() {
        return uIntro;
    }

    public void setuIntro(String uIntro) {
        this.uIntro = uIntro == null ? null : uIntro.trim();
    }

    public String getuHeadportrait() {
        return uHeadportrait;
    }

    public void setuHeadportrait(String uHeadportrait) {
        this.uHeadportrait = uHeadportrait == null ? null : uHeadportrait.trim();
    }

    public String getuShengxiao() {
        return uShengxiao;
    }

    public void setuShengxiao(String uShengxiao) {
        this.uShengxiao = uShengxiao == null ? null : uShengxiao.trim();
    }

    public Integer getuAge() {
        return uAge;
    }

    public void setuAge(Integer uAge) {
        this.uAge = uAge;
    }

    public String getuConstellation() {
        return uConstellation;
    }

    public void setuConstellation(String uConstellation) {
        this.uConstellation = uConstellation == null ? null : uConstellation.trim();
    }

    public String getuBloodtype() {
        return uBloodtype;
    }

    public void setuBloodtype(String uBloodtype) {
        this.uBloodtype = uBloodtype == null ? null : uBloodtype.trim();
    }

    public String getuSchooltag() {
        return uSchooltag;
    }

    public void setuSchooltag(String uSchooltag) {
        this.uSchooltag = uSchooltag == null ? null : uSchooltag.trim();
    }

    public String getuVocation() {
        return uVocation;
    }

    public void setuVocation(String uVocation) {
        this.uVocation = uVocation == null ? null : uVocation.trim();
    }

    public Integer getuNationid() {
        return uNationid;
    }

    public void setuNationid(Integer uNationid) {
        this.uNationid = uNationid;
    }

    public Integer getuProvinceid() {
        return uProvinceid;
    }

    public void setuProvinceid(Integer uProvinceid) {
        this.uProvinceid = uProvinceid;
    }

    public Integer getuCityid() {
        return uCityid;
    }

    public void setuCityid(Integer uCityid) {
        this.uCityid = uCityid;
    }

    public Integer getuFriendshippolicyid() {
        return uFriendshippolicyid;
    }

    public void setuFriendshippolicyid(Integer uFriendshippolicyid) {
        this.uFriendshippolicyid = uFriendshippolicyid;
    }

    public Integer getuUserstateid() {
        return uUserstateid;
    }

    public void setuUserstateid(Integer uUserstateid) {
        this.uUserstateid = uUserstateid;
    }

    public String getuFriendpolicyquestion() {
        return uFriendpolicyquestion;
    }

    public void setuFriendpolicyquestion(String uFriendpolicyquestion) {
        this.uFriendpolicyquestion = uFriendpolicyquestion == null ? null : uFriendpolicyquestion.trim();
    }

    public String getuFriendpolicyanswer() {
        return uFriendpolicyanswer;
    }

    public void setuFriendpolicyanswer(String uFriendpolicyanswer) {
        this.uFriendpolicyanswer = uFriendpolicyanswer == null ? null : uFriendpolicyanswer.trim();
    }

    public String getuFriendpolicypassword() {
        return uFriendpolicypassword;
    }

    public void setuFriendpolicypassword(String uFriendpolicypassword) {
        this.uFriendpolicypassword = uFriendpolicypassword == null ? null : uFriendpolicypassword.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getNotExpired() {
        return notExpired;
    }

    public void setNotExpired(Boolean notExpired) {
        this.notExpired = notExpired;
    }

    public Boolean getAccountNotLocked() {
        return accountNotLocked;
    }

    public void setAccountNotLocked(Boolean accountNotLocked) {
        this.accountNotLocked = accountNotLocked;
    }

    public Boolean getCredentialsNotExpired() {
        return credentialsNotExpired;
    }

    public void setCredentialsNotExpired(Boolean credentialsNotExpired) {
        this.credentialsNotExpired = credentialsNotExpired;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "uId=" + uId +
                ", uLoginid='" + uLoginid + '\'' +
                ", uNickname='" + uNickname + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uSignature='" + uSignature + '\'' +
                ", uSex=" + uSex +
                ", uBirthday=" + uBirthday +
                ", uTelephone='" + uTelephone + '\'' +
                ", uName='" + uName + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uIntro='" + uIntro + '\'' +
                ", uHeadportrait='" + uHeadportrait + '\'' +
                ", uShengxiao='" + uShengxiao + '\'' +
                ", uAge=" + uAge +
                ", uConstellation='" + uConstellation + '\'' +
                ", uBloodtype='" + uBloodtype + '\'' +
                ", uSchooltag='" + uSchooltag + '\'' +
                ", uVocation='" + uVocation + '\'' +
                ", uNationid=" + uNationid +
                ", uProvinceid=" + uProvinceid +
                ", uCityid=" + uCityid +
                ", uFriendshippolicyid=" + uFriendshippolicyid +
                ", uUserstateid=" + uUserstateid +
                ", uFriendpolicyquestion='" + uFriendpolicyquestion + '\'' +
                ", uFriendpolicyanswer='" + uFriendpolicyanswer + '\'' +
                ", uFriendpolicypassword='" + uFriendpolicypassword + '\'' +
                ", enabled=" + enabled +
                ", notExpired=" + notExpired +
                ", accountNotLocked=" + accountNotLocked +
                ", credentialsNotExpired=" + credentialsNotExpired +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
