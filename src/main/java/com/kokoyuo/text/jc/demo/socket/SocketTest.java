package com.kokoyuo.text.jc.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author lixuanwen
 * @date 2020-10-29 11:40
 */
public class SocketTest {

    public static void main(String[] args) throws IOException {
        System.out.println("聊天客户端启动--------------------->");
        Socket socket = new Socket("localhost", 7099);
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String enterInStr = scanner.nextLine();
            outputStream.write(enterInStr.getBytes(SocketConfig.charset));
            System.out.println(enterInStr);
            if (SocketConfig.EXIT_COMMAND.equalsIgnoreCase(enterInStr)){
                System.out.println(SocketConfig.GOODBYE);
                outputStream.write(SocketConfig.GOODBYE.getBytes(SocketConfig.charset));
                outputStream.close();
                socket.close();
                break;
            }
        }
        System.out.println("------------------客户端退出");
    }
}
