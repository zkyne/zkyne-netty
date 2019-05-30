package com.zkyne.zkynenetty.chapter4;

import com.zkyne.zkynenetty.chapter3.BasePacket;
import com.zkyne.zkynenetty.chapter3.PacketCodec;
import com.zkyne.zkynenetty.chapter3.packet.LoginPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @ClassName: FirstServerHandler
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/15 22:09
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + ": 客户端进行登录...");
        //  1.读取从客户端收到的数据
        ByteBuf byteBuf = (ByteBuf) msg;
        //  2.对数据进行解码
        PacketCodec packetCodec = new PacketCodec();
        BasePacket basePacket = packetCodec.decode(byteBuf);
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(basePacket.getVersion());
        if(basePacket instanceof LoginPacket){
            LoginPacket loginPacket = (LoginPacket) basePacket;
            //  3.处理登录逻辑
            if(handleLogin(loginPacket)){
                //  登录成功
                loginResponsePacket.setCode(200);
                loginResponsePacket.setMessage("Login Success");
                System.out.println(new Date() + ": 登录成功!");
            }else{
                // 登录失败
                loginResponsePacket.setCode(500);
                loginResponsePacket.setMessage("Login failure:用户名或密码有误");
                System.out.println(new Date() + ": 登录失败!");
            }
        }
        byteBuf.release();
        // 4.响应客户端
        ByteBuf responseByteBuf = packetCodec.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(responseByteBuf);
    }

    private boolean handleLogin(LoginPacket loginPacket) {
        return loginPacket != null && loginPacket.getUserId() == 1;
    }
}
