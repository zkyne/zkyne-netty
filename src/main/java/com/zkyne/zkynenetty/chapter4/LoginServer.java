package com.zkyne.zkynenetty.chapter4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName: LoginServer
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/30 16:18
 */
public class LoginServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup serverGroup = new NioEventLoopGroup();
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        serverBootstrap
                .group(serverGroup, clientGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ServerHandler());
                    }
                }).bind(8000);
    }

}
