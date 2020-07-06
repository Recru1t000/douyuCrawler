package com.recruit.danmu.douyuCrawl;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;


public class test {

    @Test
    public void xxx()
    {
        String msg = "  type@=chatmsg/rid@=5189167/ct@=1/uid@=8922578/nn@=冷傲的军/txt@=你值几块/cid@=54dd6a7549d34457e090b60100000000/ic@=avatar_v3@S201910@S93b62e0b7a4640c48b9a4c4fcebc5254/level@=16/sahf@=0/cst@=1593754376636/bnn@=歆崽/bl@=11/brid@=5189167/hc@=969a190821c9e94490f115f42b1c0d80/cbid@=178493/el@=/lk@=/fl@=11/urlev@=16/dms@=3/pdg@=54/pdk@=7/ �\u0001  �\u0001  �\u0002  type@=chatmsg/rid@=5189167/ct@=2/uid@=142128894/nn@=车底的QGM/txt@=@A椰汁球的彭于晏：你别几万块，猪肉不值钱/cid@=44a23d9303a84d8f849eb60100000000/ic@=avatar_v3@S202005@S6aadc09811364e08b315425ec07850f6/level@=17/sahf@=0/nl@=7/col@=3/cst@=1593754376323/bnn@=歆崽/bl@=11/brid@=5189167/hc@=969a190821c9e94490f115f42b1c0d80/ifs@=1/cbid@=178493/el@=/lk@=/fl@=11/urlev@=16/dms@=4/pdg@=42/pdk@=6/ k\u0001  k\u0001  �\u0002  type@=chatmsg/rid@=5189167/uid@=55287750/nn@=转身离去不见/txt@=都是炒出来的价格/cid@=a78fa0704ff54ab0e99cb60100000000/ic@=avatar@Sface@S201606@S27@S8d727b9b3c437e5303bd3a2b55fae8e6/level@=6/sahf@=0/cst@=1593754379063/bnn@=淑怡/bl@=6/brid@=290935/hc@=c9dee25e7897a464272b80678de6ea8b/cbid@=77277/el@=/lk@=/urlev@=11/dms@=4/pdg@=30/pdk@=84/";
        msg = msg.trim();
        String[] bulletScreenNum = msg.split("type@=");//通过type@=来解析字符串，第0个数组抛弃，因为为type前面的字符串，从1开始分析
        String[][] bulletScreenSplit = new String[bulletScreenNum.length][];//bulletScreenSplit将每个字符串都分后的字符串二维数组，同样第0位抛弃
        for(int i=0;i<bulletScreenNum.length;i++)
        {
            bulletScreenSplit[i] = bulletScreenNum[i].split("/");
        }
        System.out.println(bulletScreenSplit[1][0]);
        for(String[] t:bulletScreenSplit)
        {

        }
    }

    @Test
    public void ttt()
    {
        String a = "uid@=56";
        System.out.println(a.substring(5));
        String b = "bnn@=";
        System.out.println(b.substring(5));
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");//定义时间的格式

        String x = f.format(new Date());
        System.out.println(x);
        // 假如这是前台传来的Date时间



        long oneDayTime = 1000*3600*24;
        String date = f.format(new Date(new Date().getTime()-oneDayTime));
        System.out.println(date);
    }
    @Test
    public void crawl() throws URISyntaxException {
        WebSocketClient websocketclient = new WebSocketClient(new URI("wss://danmuproxy.douyu.com:8506/"
        ), new Draft_6455()) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                try {
                    send(login());//发送登录请求
                    send(joinGroup());//发送加入群组请求
                    send(heartBeat());//发送心跳
                    Thread heartBeatThread = new Thread(() -> {
                        while (true)
                        {
                            try {
                                send(heartBeat());
                                System.out.println("发送心跳");
                                Thread.sleep(45000);
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    heartBeatThread.start();
                } catch (IOException e) {

                    e.printStackTrace();
                }
                System.out.println("打开连接");
            }

            @Override
            public void onMessage(String message) {
            }
            public void onMessage(ByteBuffer bytes)
            {
                Charset charset = StandardCharsets.UTF_8;
                CharBuffer charBuffer = charset.decode(bytes);
                String s = charBuffer.toString();
                System.out.println(s);
            }
            @Override
            public void onClose(int i, String s, boolean b) {
                System.out.println("连接关闭");
            }
            @Override
            public void onError(Exception e) {
                System.out.println("发生错误");
            }};
        websocketclient.run();
    }

    public byte[] login() throws IOException {
        String message = "type@=loginreq/roomid@=5189167/";
        return douyuRequestEncode(message);
    }
    //加入群组请求
    public byte[] joinGroup() throws IOException{
        String message ="type@=joingroup/rid@=123456/gid@=-9999/";
        return douyuRequestEncode(message);
    }
    //心跳
    public byte[] heartBeat() throws IOException{
        String message = "type@=mrkl/";
        return douyuRequestEncode(message);
    }

    //将传入的数据变成符合斗鱼协议要求的字节流返回
    public byte[] douyuRequestEncode(String message) throws IOException {
        int dataLen1 = message.length() + 9;//4 字节小端整数，表示整条消息（包括自身）长度（字节数）。
        int dataLen2 = message.length() + 9;//消息长度出现两遍，二者相同。
        int send = 689;//689 客户端发送给弹幕服务器的文本格式数据,暂时未用，默认为 0。保留字段：暂时未用，默认为 0。
        byte[] msgBytes= message.getBytes(StandardCharsets.UTF_8);
        int end = 0;
        byte[] endBytes = new byte[1];
        endBytes[0] = (byte) (end  & 0xFF);;//结尾必须为‘\0’。详细序列化、反序列化

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bytes.write(intToBytesLittle(dataLen1));
        bytes.write(intToBytesLittle(dataLen2));
        bytes.write(intToBytesLittle(send));
        bytes.write(msgBytes);
        bytes.write(endBytes);
        //返回byte[]
        return bytes.toByteArray();
    }

    //将整形转化为4位小端字节流
    public  byte[] intToBytesLittle(int value) {
        return new byte [] {
                (byte) (value & 0xFF),
                (byte) ((value >> 8) & 0xFF),
                (byte) ((value >> 16) & 0xFF),
                (byte) ((value >> 24) & 0xFF)
        };
    }
    /**
    @Test
    public void qest() throws URISyntaxException, IOException {
        douyuConnect x = new douyuConnect("5189167");
        WebSocketClient t = x.createConnect();
        t.run();
    }
    */
}

