package com.zkyne.zkynenetty.chapter3;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName: JSONSerializer
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/30 14:33
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.FASTJSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
