package com.recruit.domain;

public class gift {
    private Integer giftId;
    private Integer value;

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "gift{" +
                "giftId=" + giftId +
                ", value=" + value +
                '}';
    }
}
