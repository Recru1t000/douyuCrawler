package com.recruit.domain;

import java.util.Date;

public class nobleNumInfo {
    private Integer id=0;
    private Integer nobility;
    private Integer total;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNobility() {
        return nobility;
    }

    public void setNobility(Integer nobility) {
        this.nobility = nobility;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "nobleNumInfoDao{" +
                "id=" + id +
                ", nobility=" + nobility +
                ", total=" + total +
                ", createTime=" + createTime +
                '}';
    }
}
