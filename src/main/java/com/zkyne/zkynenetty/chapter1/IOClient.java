package com.zkyne.zkynenetty.chapter1;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: IOClient
 * @Description: 传统IO 客户端
 * @Author: zkyne
 * @Date: 2019/5/13 23:44
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(()->{
            try{
                Socket socket = new Socket("127.0.0.1", 8000);
                while(true){
                    try{
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        TimeUnit.SECONDS.sleep(2);
                    }catch (Exception e){
                        //
                    }
                }
            }catch (IOException e){
                //
            }

        }).start();
    }
}
