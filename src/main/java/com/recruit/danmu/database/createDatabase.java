package com.recruit.danmu.database;

import com.recruit.dao.IcreateDatabaseDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class createDatabase {
    public void createTable() throws ParseException, IOException {
        //连接配置
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = factory.openSession();
        IcreateDatabaseDao createDatabaseDao = session.getMapper(IcreateDatabaseDao.class);


        //日期字符串
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");//定义时间的格式
        String date = f.format(d);

        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String  aDate=null;
            if(i<10)
            {
                aDate = year +"0"+month+"0"+i;
            }
            else {
                aDate = year +"0"+month+i;};
            //创建库
            createDatabaseDao.createChatMsg(aDate);
            createDatabaseDao.createNobleNumInfo(aDate);
            createDatabaseDao.createDbg(aDate);
        }

        //连接关闭
        session.commit();
        session.close();
        in.close();

    }
}
