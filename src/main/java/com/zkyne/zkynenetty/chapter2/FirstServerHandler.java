package com.zkyne.zkynenetty.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @ClassName: FirstServerHandler
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/15 22:09
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读取从客户端收到的数据
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ":服务端读到数据--->>>>" + byteBuf.toString(Charset.forName("utf-8")));
        byteBuf.release();
        // 回复数据给客户端
        System.out.println(new Date() + ":服务端写数据");
        ByteBuf out = HandlerUtils.getByteBuf(ctx,"你也好呀,客户端");
        ctx.channel().writeAndFlush(out);
    }
}
