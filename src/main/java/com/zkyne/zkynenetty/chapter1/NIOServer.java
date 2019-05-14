package com.zkyne.zkynenetty.chapter1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: NIOServer
 * @Description:
 * @Author: zkyne
 * @Date: 2019/5/14 20:55
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(()->{
            try{
                // 对应IO中的服务端启动
                ServerSocketChannel listenChannel = ServerSocketChannel.open();
                listenChannel.socket().bind(new InetSocketAddress(8000));
                listenChannel.configureBlocking(false);
                listenChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while(true){
                    // 监测是否有新链接,这里的1 指的的阻塞时间是1ms
                    if(serverSelector.select(1) > 0){
                        Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while(iterator.hasNext()){
                            SelectionKey selectionKey = iterator.next();
                            if(selectionKey.isAcceptable()){
                                try{
                                    // 每次来一个新连接,不需要创建线程,直接注册到clientSelector
                                    SocketChannel clientChannel = ((ServerSocketChannel)selectionKey.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                }finally {
                                    iterator.remove();
                                }
                            }
                        }
                    }
                }
            }catch (IOException e){
                //
            }

        }).start();

        new Thread(()->{
            try{
                while(true){
                    // 轮询有哪些链接有信息可读取
                    if(clientSelector.select(1) > 0){
                        Set<SelectionKey> selectionKeys = clientSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        if(iterator.hasNext()){
                            SelectionKey selectionKey = iterator.next();
                            if(selectionKey.isReadable()){
                                try{
                                    // 读取消息
                                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    socketChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());

                                }finally {
                                    //
                                    iterator.remove();
                                    selectionKey.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                    }
                }
            }catch (IOException e){
                //
            }
        }).start();
    }
}
