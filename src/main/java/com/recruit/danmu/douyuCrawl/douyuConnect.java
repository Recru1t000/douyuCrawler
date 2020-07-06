package com.recruit.danmu.douyuCrawl;
import com.recruit.danmu.database.insertData;
import com.recruit.danmu.messageAnalysis.analysisInit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;

public class douyuConnect {
    private final String roomId;

    //类加载器,要求创建连接时必须要赋予roomId
    public douyuConnect(String roomId)
    {
        this.roomId = roomId;
    }

    public WebSocketClient createConnect() throws URISyntaxException, IOException {
        return new WebSocketClient(new URI("wss://danmuproxy.douyu.com:8506/"), new Draft_re()) {
            final insertData insert = new insertData();
            final analysisInit analysis = new analysisInit(insert);
            final douyuRequest request = new douyuRequest();
            final Thread heartBeatThread = new Thread(() -> {
                while (true)
                {
                    try {
                        send(request.heartBeat());
                        System.out.println("发送心跳");
                        Thread.sleep(45000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                try {
                    send(request.login(roomId));//发送登录请求
                    send(request.joinGroup(roomId));//发送加入群组请求
                    send(request.heartBeat());//发送心跳
                    heartBeatThread.start();
                } catch (IOException e) {

                    e.printStackTrace();
                }

                System.out.println("打开连接");
            }

            @Override
            public void onMessage(String message) {

            }

            @Override
            public void onMessage(ByteBuffer byteBuffer) {
                Charset charset = StandardCharsets.UTF_8;
                CharBuffer charBuffer = charset.decode(byteBuffer);
                String s = charBuffer.toString();
                String[] bulletScreenNum = s.split("type@=");//通过type@=来解析字符串，第0个数组抛弃，因为为type前面的字符串，从1开始分析
                for (String value : bulletScreenNum) {
                    try {
                        analysis.Init(value.split("/"));
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                }
                //System.out.println(s.indexOf("type@="));
                //System.out.println("收到的消息"+s);
                //System.out.println("换行");
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                System.out.println(i);
                System.out.println("连接关闭");
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                System.out.println("发生错误已关闭");
            }
        };
    }
}
