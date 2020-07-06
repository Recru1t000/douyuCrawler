package com.recruit;

import com.recruit.danmu.database.createDatabase;
import com.recruit.danmu.douyuCrawl.douyuConnect;
import com.recruit.dao.IcreateDatabaseDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.java_websocket.client.WebSocketClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class __init__ {

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
        createDatabase createdatabase = new createDatabase();
        createdatabase.createTable();
        douyuConnect douyuconnect = new douyuConnect("5189167");
        WebSocketClient t = douyuconnect.createConnect();
        t.connect();

    }
}
