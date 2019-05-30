package com.zkyne.zkynenetty.chapter3;

import lombok.Data;

/**
 * @ClassName: BasePacket
 * @Description: 自定义通信协议的数据包对象
 * @Author: zkyne
 * @Date: 2019/5/30 13:57
 */
@Data
public abstract class BasePacket {

    /**
     * 协议版本
     */
    private byte version=1;

    /**
     * 获取指令
     * @return
     */
    public abstract byte getCommand();
}
