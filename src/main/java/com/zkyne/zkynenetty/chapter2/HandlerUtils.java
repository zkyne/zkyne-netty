package com.zkyne.zkynenetty.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.NonNull;

import java.nio.charset.Charset;

/**
 * @ClassName: HandlerUtils
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/15 22:18
 */
public class HandlerUtils {

    public static ByteBuf getByteBuf(ChannelHandlerContext ctx,@NonNull String message) {
        // 获取二进制抽象对象
        ByteBuf buffer = ctx.alloc().buffer();
        // 准备写入数据
        byte[] bytes = message.getBytes(Charset.forName("UTF-8"));
        // 填充数据到buffer
        buffer.writeBytes(bytes);
        return buffer;
    }

}
