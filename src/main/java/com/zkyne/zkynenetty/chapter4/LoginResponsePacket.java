package com.zkyne.zkynenetty.chapter4;

import com.zkyne.zkynenetty.chapter3.BasePacket;
import com.zkyne.zkynenetty.chapter3.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: LoginResponsePacket
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/30 16:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResponsePacket extends BasePacket {
    private int code;
    private String message;


    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONCE;
    }
}
