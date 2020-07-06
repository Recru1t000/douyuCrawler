package com.recruit.dao;

import com.recruit.domain.dbg;
import com.recruit.domain.nobleNumInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InobleNumInfoDao {
    @Insert("<script>" +
            "INSERT noblenuminfo${nowDate} (nobility,total,createTime) VALUES" +
            "<foreach collection='noblenuminfo' open='' item='noblenuminfo' separator=',' close=''> " +
            "(#{noblenuminfo.nobility},#{noblenuminfo.total},#{noblenuminfo.createTime})" +
            "</foreach>" +
            "</script>")
    public void saveNobleNumInfo(@Param("nowDate") String nowDate, @Param("noblenuminfo") List<nobleNumInfo> noblenuminfo);
}
