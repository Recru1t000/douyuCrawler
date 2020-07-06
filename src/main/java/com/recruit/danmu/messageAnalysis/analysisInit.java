package com.recruit.danmu.messageAnalysis;

import com.recruit.danmu.database.insertData;
import com.recruit.domain.chatMsg;
import com.recruit.domain.dbg;
import com.recruit.domain.nobleNumInfo;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class analysisInit {
    private final List<chatMsg> chatMsgList = new ArrayList<>();
    private final List<nobleNumInfo> nobleNumInfoList= new ArrayList<>();
    private final List<dbg> dbgList= new ArrayList<>();
    private final insertData insert;
    private int oldDay = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")).get(Calendar.DATE);
    private final SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");//定义时间的格式

    public analysisInit(insertData insert)
    {
        this.insert = insert;
    }
    public void Init(String[] messages) throws IOException, ParseException {
        if(oldDay!=Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")).get(Calendar.DATE))
        {
            long oneDayTime = 1000*3600*24;
            String date = f.format(new Date(new Date().getTime()-oneDayTime));
            insert.insertChatMsg(date,chatMsgList);
            chatMsgList.clear();
            insert.insertNobleNumInfo(date,nobleNumInfoList);
            nobleNumInfoList.clear();
            insert.insertDbg(date,dbgList);
            dbgList.clear();
            oldDay = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")).get(Calendar.DATE);
            //如果在新加入时，日期对不上的话就清空表，将原来list中的数据加入前一天的日期
        }
        if(chatMsgList.size()==50)
        {
            String date = f.format(new Date());
            insert.insertChatMsg(date,chatMsgList);
            System.out.println("chatMsg容量已满，上传至数据库");
            chatMsgList.clear();
            System.out.println("chatMsg列表已清空");
        }
        if(nobleNumInfoList.size()==50)
        {
            String date = f.format(new Date());
            insert.insertNobleNumInfo(date,nobleNumInfoList);
            System.out.println("nobleNumInfo容量已满，上传至数据库");
            nobleNumInfoList.clear();
            System.out.println("nobleNumInfo列表已清空");
        }
        if(dbgList.size()==50)
        {
            String date = f.format(new Date());
            insert.insertDbg(date,dbgList);
            System.out.println("dbg容量已满，上传至数据库");
            dbgList.clear();
            System.out.println("dbg列表已清空");
        }
        switch (messages[0]) {
            //弹幕发言
            case "chatmsg":
                chatMsgAnalysis(messages);
                break;
            //贵宾人数
            case "noble_num_info":
                nobleNumInfoAnalysis(messages);
                break;
            //礼物信息
            case "dgb":
                dbgAnalysis(messages);
                break;
        }
    }

    //弹幕发言解析
    public void chatMsgAnalysis(String[] messages) throws ParseException {
        chatMsg chatmsg = new chatMsg();
        for(String message:messages) {
            if (message.startsWith("uid")) {
                chatmsg.setUserId(message.substring(5));//uid@=
            } else if (message.startsWith("nn")) {
                chatmsg.setUserName(message.substring(4));//nn@=
            } else if (message.startsWith("txt")) {
                chatmsg.setTxt(message.substring(5));//txt@=
            } else if (message.startsWith("level")) {
                chatmsg.setUserLevel(Integer.valueOf(message.substring(7)));//level@=
            } else if (message.startsWith("bnn")) {
                if (message.length() != 5)//长度不为5说明后面有字符串，否则默认null
                    chatmsg.setBnn(message.substring(5));//bnn@=
            } else if (message.startsWith("bl")) {
                if (message.length() != 4)//长度不为4说明后面有字符串，否则默认null
                    chatmsg.setBl(Integer.valueOf(message.substring(4)));//bl@=
                break;//后面不用继续遍历了，只取这么多数据
            }
        }
        SimpleDateFormat f = new SimpleDateFormat("HHmmss");//定义时间的格式
        String date = f.format(new Date());
        chatmsg.setCreateTime(f.parse(date));
        //System.out.println(chatmsg);
        chatMsgList.add(chatmsg);
        //System.out.println("chatMsgList:"+chatMsgList.size());
    }

    //贵宾人数解析
    public void nobleNumInfoAnalysis(String[] messages) throws ParseException {
        nobleNumInfo noblenuminfo = new nobleNumInfo();
        for(String message:messages)
        {
            if (message.startsWith("sum"))
            {
                noblenuminfo.setNobility(Integer.valueOf(message.substring(5)));//sum@=
            }
            else if (message.startsWith("vn"))
            {
                noblenuminfo.setTotal(Integer.valueOf(message.substring(4)));//vn@=
            }
        }
        SimpleDateFormat f = new SimpleDateFormat("HHmmss");//定义时间的格式
        String date = f.format(new Date());
        noblenuminfo.setCreateTime(f.parse(date));
        //System.out.println(noblenuminfo);
        nobleNumInfoList.add(noblenuminfo);
        //System.out.println("nobleNumInfoList:"+nobleNumInfoList.size());
    }
    //收到礼物解析
    public void dbgAnalysis(String[] messages) throws ParseException {
        dbg dbg_ = new dbg();
        for(String message:messages)
        {
            if (message.startsWith("gfid"))
            {
                dbg_.setGiftId(Integer.valueOf(message.substring(6)));//gfid@=
            }
            else if (message.startsWith("uid"))
            {
                dbg_.setUserId(message.substring(5));//uid@=
            }
            else if (message.startsWith("nn"))
            {
                dbg_.setUserName(message.substring(4));//nn@=
            }
            else if (message.startsWith("gfcnt"))
            {
                dbg_.setGiftNum(Integer.valueOf(message.substring(7)));//gfcnt@=
            }
        }
        SimpleDateFormat f = new SimpleDateFormat("HHmmss");//定义时间的格式
        String date = f.format(new Date());
        dbg_.setCreateTime(f.parse(date));
        //System.out.println(dbg_);
        dbgList.add(dbg_);
        //System.out.println("dbgList:"+dbgList.size());
    }
}
