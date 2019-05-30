package com.zkyne.zkynenetty.chapter3.packet;

import com.zkyne.zkynenetty.chapter3.BasePacket;
import com.zkyne.zkynenetty.chapter3.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: LoginPacket
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/30 14:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginPacket extends BasePacket {
    private Long userId;
    private String username;
    private String password;

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
