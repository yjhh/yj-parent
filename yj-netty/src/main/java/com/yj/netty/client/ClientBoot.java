package com.yj.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientBoot {

    private String host;

    private int port;

    public ClientBoot(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException {
        ClientHandle handle = new ClientHandle();
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group).channel(NioSocketChannel.class)
                .remoteAddress(host,port)
                .handler(handle);
        ChannelFuture future = bootstrap.connect().sync();
        future.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
