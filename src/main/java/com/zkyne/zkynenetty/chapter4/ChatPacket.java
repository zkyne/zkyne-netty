package com.zkyne.zkynenetty.chapter4;

import com.zkyne.zkynenetty.chapter3.BasePacket;
import com.zkyne.zkynenetty.chapter3.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: ChatPacket
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/31 11:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatPacket extends BasePacket {
    private String message;

    @Override
    public byte getCommand() {
        return Command.CHAT;
    }
}
