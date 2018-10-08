package com.yj.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerNio {

    private int port;

    public ServerNio(int port){
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            SimpleServerChannelHandle handle= new SimpleServerChannelHandle();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port).childHandler(handle);
            ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        }finally{
            group.shutdownGracefully();
        }
    }

}
