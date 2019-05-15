package com.zkyne.zkynenetty.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @ClassName: FirstClientHandler
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/15 21:59
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ":客户端写数据");
        // 1获取数据
        ByteBuf byteBuffer = HandlerUtils.getByteBuf(ctx,"你好,服务端");
        // 2写数据
        ctx.channel().writeAndFlush(byteBuffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读取从服务端收到的数据
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ":客户端读到数据--->>>>>" + byteBuf.toString(Charset.forName("utf-8")));
        byteBuf.release();
    }
}
