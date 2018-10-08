package com.yj.netty;

import com.yj.netty.client.ClientBoot;
import com.yj.netty.server.ServerNio;
import org.junit.Test;

public class ServerTest {

    @Test
    public void server() throws InterruptedException {
        ServerNio nio = new ServerNio(8888);
        nio.start();
    }

    @Test
    public void client() throws InterruptedException {
        ClientBoot boot = new ClientBoot("127.0.0.1",8888);
        boot.run();
    }
}
