package com.zkyne.zkynenetty.chapter4;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * @ClassName: LoginUtil
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/31 14:14
 */
public class LoginUtil {
    private static final AttributeKey<Boolean> ATTRIBUTE_LOGIN = AttributeKey.newInstance("login");

    public static void markAsLogin(Channel channel) {
        channel.attr(ATTRIBUTE_LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(ATTRIBUTE_LOGIN);
        return loginAttr.get() != null && loginAttr.get();
    }
    public static void exit(Channel channel){
        channel.attr(ATTRIBUTE_LOGIN).set(false);
    }
}
