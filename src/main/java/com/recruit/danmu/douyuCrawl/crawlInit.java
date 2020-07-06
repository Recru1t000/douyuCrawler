package com.recruit.danmu.douyuCrawl;

import org.java_websocket.client.WebSocketClient;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class crawlInit {
    public static void main(String[] args) throws URISyntaxException, IOException {
        douyuConnect x = new douyuConnect("74751");
        WebSocketClient t = x.createConnect();
        t.connect();

    }
    /**
    @Test
    public void xxx()
    {
        int[] s = {1,2,3,4,5,6,7,8,9};
        for(int i: Arrays.copyOfRange(s,1,4))
        {
            System.out.println(i);
        }
    }
    */
}
