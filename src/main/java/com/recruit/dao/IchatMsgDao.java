package com.recruit.dao;

import com.recruit.domain.chatMsg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface IchatMsgDao {
    @Insert("<script>" +
            "INSERT chatmsg${nowDate} (userName,userId,txt,userLevel,bnn,bl,createTime) VALUES" +
            "<foreach collection='chatmsg' open='' item='chatmsg' separator=',' close=''> " +
            "(#{chatmsg.userName},#{chatmsg.userId},#{chatmsg.txt},#{chatmsg.userLevel},#{chatmsg.bnn},#{chatmsg.bl},#{chatmsg.createTime})" +
            "</foreach>" +
            "</script>")
    public void saveChatMsg(@Param("nowDate") String nowDate,@Param("chatmsg") List<chatMsg> chatmsg);
}
