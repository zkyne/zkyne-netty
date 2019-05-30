package com.zkyne.zkynenetty.chapter3;

import com.google.common.collect.Maps;
import com.zkyne.zkynenetty.chapter3.packet.LoginPacket;
import com.zkyne.zkynenetty.chapter4.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.Map;

/**
 * @ClassName: PacketCodec
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/30 14:46
 */
public class PacketCodec {
    private static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends BasePacket>> PACKET_TYPE_MAP;
    private static final Map<Byte, Serializer> SERIALIZER_MAP;
    private Byte curSerializerAlgorithm;

    static {
        PACKET_TYPE_MAP = Maps.newHashMap();
        PACKET_TYPE_MAP.put(Command.LOGIN_REQUEST, LoginPacket.class);
        PACKET_TYPE_MAP.put(Command.LOGIN_RESPONCE, LoginResponsePacket.class);
        SERIALIZER_MAP = Maps.newHashMap();
        SERIALIZER_MAP.put(Serializer.DEFAULT_SERIALIZER.getSerializerAlgorithm(), Serializer.DEFAULT_SERIALIZER);
    }

    public PacketCodec() {
        this.curSerializerAlgorithm = Serializer.DEFAULT_SERIALIZER.getSerializerAlgorithm();
        if(!SERIALIZER_MAP.containsKey(Serializer.DEFAULT_SERIALIZER.getSerializerAlgorithm())){
            SERIALIZER_MAP.put(Serializer.DEFAULT_SERIALIZER.getSerializerAlgorithm(), Serializer.DEFAULT_SERIALIZER);
        }
    }

    public PacketCodec(int curSerializerAlgorithm) {
       // 待处理其他序列化
    }

    /**
     * 编码
     * |    魔数(0x12345678)  |   版本号(1)  |   序列化算法   |   指令  |   数据长度    |   数据  |
     * |    4字节             |   1字节      |   1字节        |   1字节 |   4字节      |  N字节  |
     *
     * @param basePacket
     * @return
     */
    public ByteBuf encode(BasePacket basePacket) {
        ByteBufAllocator allocator = ByteBufAllocator.DEFAULT;
        return encode(allocator,basePacket);
    }

    public ByteBuf encode(ByteBufAllocator allocator,BasePacket basePacket) {
        //  1.创建ByteBuf对象
        ByteBuf byteBuf = allocator.ioBuffer();
        //  2.序列化 java对象
        byte[] bytes = SERIALIZER_MAP.get(this.curSerializerAlgorithm).serialize(basePacket);
        //  3.实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(basePacket.getVersion());
        byteBuf.writeByte(this.curSerializerAlgorithm);
        byteBuf.writeByte(basePacket.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public BasePacket decode(ByteBuf byteBuf) {
        //  1.跳过魔数 magicnumber
        byteBuf.skipBytes(4);
        //  2.跳过版本号
        byteBuf.skipBytes(1);
        //  3.序列化算法标识
        byte serializerAlgorithm = byteBuf.readByte();
        //  4.指令标识
        byte command = byteBuf.readByte();
        //  5.数据包长度
        int length = byteBuf.readInt();
        //  6.接收数据
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Class<? extends BasePacket> basePacketClazz = getBasePacketClazz(command);
        Serializer serializer = getSerializer(serializerAlgorithm);
        if(basePacketClazz != null && serializer != null){
            return serializer.deserialize(basePacketClazz, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return SERIALIZER_MAP.get(serializeAlgorithm);
    }

    private Class<? extends BasePacket> getBasePacketClazz(byte command) {
        return PACKET_TYPE_MAP.get(command);
    }
}
