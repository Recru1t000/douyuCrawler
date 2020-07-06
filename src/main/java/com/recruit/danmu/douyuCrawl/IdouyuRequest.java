package com.recruit.danmu.douyuCrawl;

import java.io.IOException;

public interface IdouyuRequest {
    public byte[] login(String roomId) throws IOException;

    public byte[] joinGroup(String roomId) throws IOException;

    public byte[] heartBeat() throws IOException;

}
