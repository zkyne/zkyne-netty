package com.zkyne.zkynenetty.chapter1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: IOServer
 * @Description: 传统IO 服务器端
 * @Author: zkyne
 * @Date: 2019/5/13 23:35
 */
public class IOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        // 接收新连接课程
        new Thread(()->{
            while(true){
                try{
                    // 阻塞方法获取链接
                    Socket socket = serverSocket.accept();

                    // 每一个新的链接创建一个新线程,负责读取数据
                    new Thread(()->{
                        try{
                            int len;
                            byte[] bytes = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // 按字节流方式读取数据
                            while((len = inputStream.read(bytes)) != -1){
                                System.out.println(new String(bytes, 0,len));
                            }
                        }catch (IOException e){
                            //
                        }
                    }).start();
                }catch (IOException e){
                    //
                }
            }
        }).start();
    }
}
