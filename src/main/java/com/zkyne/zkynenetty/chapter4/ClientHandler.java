package com.zkyne.zkynenetty.chapter4;

import com.zkyne.zkynenetty.chapter3.BasePacket;
import com.zkyne.zkynenetty.chapter3.PacketCodec;
import com.zkyne.zkynenetty.chapter3.packet.LoginPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @ClassName: ClientHandler
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/30 16:20
 */
public class ClientHandler extends ChannelInboundHandlerAdapter{
    /**
     * 链接成功后回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ":客户端开始登录");
        //  1.创建登录对象
        LoginPacket loginPacket = new LoginPacket();
        loginPacket.setUserId(1L);
        loginPacket.setUsername("admin");
        loginPacket.setPassword("admin");
        //  2.编码
        PacketCodec packetCodec = new PacketCodec();
        ByteBuf byteBuf = packetCodec.encode(ctx.alloc(), loginPacket);
        //  3.写数据
        ctx.channel().writeAndFlush(byteBuf);
    }

    /**
     * 接收登录结果
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        PacketCodec packetCodec = new PacketCodec();
        BasePacket basePacket = packetCodec.decode(byteBuf);
        if (basePacket instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) basePacket;
            if (loginResponsePacket.getCode() == 200) {
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getMessage());
            }
        }
        byteBuf.release();
    }
}
