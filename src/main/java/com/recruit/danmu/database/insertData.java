package com.recruit.danmu.database;

import com.recruit.dao.IchatMsgDao;
import com.recruit.dao.IcreateDatabaseDao;
import com.recruit.dao.IdbgDao;
import com.recruit.dao.InobleNumInfoDao;
import com.recruit.domain.chatMsg;
import com.recruit.domain.dbg;
import com.recruit.domain.nobleNumInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class insertData {
    private final InputStream in;
    private final SqlSessionFactory factory;
    private final SqlSession session;
    private final IchatMsgDao chatMsgDao;
    private final IdbgDao dbgDao;
    private final InobleNumInfoDao nobleNumInfoDao;

    //连接配置
    public insertData() throws IOException {
        //连接配置
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        chatMsgDao = session.getMapper(IchatMsgDao.class);
        dbgDao = session.getMapper(IdbgDao.class);
        nobleNumInfoDao = session.getMapper(InobleNumInfoDao.class);
    }

    //结束时配置
    public void endConnection() throws IOException {
        session.close();
        in.close();
    }

    //弹幕发言写入数据库
    public void insertChatMsg(String date,List<chatMsg> chatMsgList) throws IOException {
        chatMsgDao.saveChatMsg(date,chatMsgList);
        session.commit();
    }

    //贵宾记录写入数据库
    public void insertNobleNumInfo(String date,List<nobleNumInfo> nobleNumInfoList) throws IOException {
        nobleNumInfoDao.saveNobleNumInfo(date,nobleNumInfoList);
        session.commit();
    }

    //送礼记录写入数据库
    public void insertDbg(String date,List<dbg> dbgList) throws IOException {
        dbgDao.saveDbg(date,dbgList);
        session.commit();
    }

}
