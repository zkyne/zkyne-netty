package com.zkyne.zkynenetty.chapter3;

/**
 * @ClassName: SerializerAlgorithm
 * @Description: 序列化算法常量
 * @Author: zkyne
 * @Date: 2019/5/30 14:32
 */
public interface SerializerAlgorithm {
    /**
     * fastJson序列化
     */
    byte FASTJSON = 1;
    /**
     * hessian 序列化
     */
    byte HESSIAN = 2;
    /**
     * Kryo 序列化(高效,推荐)
     */
    byte KRYO = 3;
    /**
     * Fst 序列化
     */
    byte FST = 4;
    /**
     * Protostuff 序列化
     */
    byte PROTOSTUFF = 5;
    /**
     * ProtoBuf 序列化
     */
    byte PROTOBUF = 6;
    /**
     * Thrift 序列化
     */
    byte THRIFT = 7;
    /**
     * Avro 序列化
     */
    byte AVRO = 8;
    /**
     * MsgPack 序列化
     */
    byte MSGPACK = 9;
}
