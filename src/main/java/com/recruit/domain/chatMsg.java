package com.recruit.domain;

import java.util.Date;

public class chatMsg {
    private Integer id=0;
    private String userName;
    private String userId;
    //弹幕内容
    private String txt=null;
    //用户等级
    private Integer userLevel=null;
    //用户所带粉丝牌
    private String bnn=null;
    //粉丝牌等级
    private Integer bl=null;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getBnn() {
        return bnn;
    }

    public void setBnn(String bnn) {
        this.bnn = bnn;
    }

    public Integer getBl() {
        return bl;
    }

    public void setBl(Integer bl) {
        this.bl = bl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "chatMsg{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", txt='" + txt + '\'' +
                ", userLevel=" + userLevel +
                ", bnn='" + bnn + '\'' +
                ", bl=" + bl +
                ", createTime=" + createTime +
                '}';
    }
}
