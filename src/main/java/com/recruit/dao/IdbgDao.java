package com.recruit.dao;

import com.recruit.domain.chatMsg;
import com.recruit.domain.dbg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdbgDao {
    @Insert("<script>" +
            "INSERT dbg${nowDate} (userName,userId,giftId,giftNum,createTime) VALUES" +
            "<foreach collection='dbg_' open='' item='dbg_' separator=',' close=''> " +
            "(#{dbg_.userName},#{dbg_.userId},#{dbg_.giftId},#{dbg_.giftNum},#{dbg_.createTime})" +
            "</foreach>" +
            "</script>")
    public void saveDbg(@Param("nowDate") String nowDate, @Param("dbg_") List<dbg> dbg_);
}
