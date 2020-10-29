package com.kokoyuo.text.jc.demo.socket;

import org.apache.xmlbeans.impl.common.ReaderInputStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author lixuanwen
 * @date 2020-10-29 11:44
 */
public class SocketServiceTest {
    public static void main(String[] args) throws IOException {
        System.out.println("聊天服务器start----------->");
        ServerSocket serverSocket = new ServerSocket(7099);
        while (true){
            // 收到消息
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            String msg = readMsg(inputStream);
            if (SocketConfig.GOODBYE.equalsIgnoreCase(msg)){
                System.out.println("客户端退出");
            }
            inputStream.close();
            System.out.println("来自客户端的消息:" + msg);
        }
    }

    public static String readMsg(InputStream inputStream) throws IOException {
        int length = inputStream.read();
        byte[] bs = new byte[length];
        inputStream.read(bs);
        String msg = new String(bs, SocketConfig.charset);
        return msg;
    }
}
