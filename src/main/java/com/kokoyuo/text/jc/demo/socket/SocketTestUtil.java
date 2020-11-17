package com.kokoyuo.text.jc.demo.socket;

import org.fusesource.jansi.Ansi;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.Random;

/**
 * @author lixuanwen
 * @date 2020-11-04 11:44
 */
public class SocketTestUtil {

    public static void main(String[] args) {
        Ansi.Color randomColor = findRandomColor();
        System.out.println(Ansi.ansi().bg(randomColor).a("测试数据").reset());

        Ansi.Color[] values = Ansi.Color.values();
        for (int i = 0; i <values.length ; i++) {
            System.out.println(Ansi.ansi().fg(values[i]).a("测试数据"+i).reset());
        }
    }

    public static Ansi.Color findRandomColor(){
        Random rd = new Random();
        int i = rd.nextInt(9);
        return Ansi.Color.values()[i];
    }

    public static void out(String s){
        ClientUser localUser = ClientContext.findLocalUser();
        if (localUser != null){
            Ansi msg = Ansi.ansi().fg(localUser.getColor()).a(s).reset();
            System.out.println(msg);
            MessageUtil.addMsg2Content(localUser, msg.toString());
        }
    }
}
