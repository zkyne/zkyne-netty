package com.zkyne.zkynenetty.chapter3;

/**
 * @ClassName: Serializer
 * @Description: 序列化
 * @Author: zkyne
 * @Date: 2019/5/30 14:12
 */
public interface Serializer {

    byte JAON_SERIALIZER = 1;

    Serializer DEFAULT_SERIALIZER = new JSONSerializer();


    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * java对象序列化成二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制反序列化成java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz,byte[] bytes);

}
