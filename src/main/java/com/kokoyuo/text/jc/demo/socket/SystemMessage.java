package com.kokoyuo.text.jc.demo.socket;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lixuanwen
 * @date 2020-11-04 21:33
 */
@Data
public class SystemMessage implements Serializable {

    private String msg;

    private ClientUser user;
}
