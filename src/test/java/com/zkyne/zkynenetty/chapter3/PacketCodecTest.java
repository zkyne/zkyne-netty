package com.zkyne.zkynenetty.chapter3;

import com.zkyne.zkynenetty.chapter3.packet.LoginPacket;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName: PacketCodecTest
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/30 15:57
 */
public class PacketCodecTest {

    @Test
    public void encode() {
        Serializer serializer = new JSONSerializer();
        LoginPacket loginPacket = new LoginPacket();
        loginPacket.setVersion(((byte) 1));
        loginPacket.setUserId(12345L);
        loginPacket.setUsername("zhangsan");
        loginPacket.setPassword("password");
        System.out.println(loginPacket);
        PacketCodec packetCodec = new PacketCodec();
        ByteBuf byteBuf = packetCodec.encode(loginPacket);
        BasePacket decodedPacket = packetCodec.decode(byteBuf);
        System.out.println(decodedPacket);
        Assert.assertArrayEquals(serializer.serialize(loginPacket), serializer.serialize(decodedPacket));
    }
}