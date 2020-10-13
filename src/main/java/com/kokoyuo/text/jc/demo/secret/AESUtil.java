package com.kokoyuo.text.jc.demo.secret;

/**
 * @author lixuanwen
 * @date 2020-05-19 17:59
 */

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * AES算法编程实现
 * @author xzb
 *
 */
public class AESUtil {

    /**
     * 生成密钥
     * @throws Exception
     */
    public static byte[] initKey() throws Exception{
        //密钥生成器
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        //初始化密钥生成器
        keyGen.init(128, new SecureRandom("text".getBytes(StandardCharsets.UTF_8)));  //默认128，获得无政策权限后可用192或256
        //生成密钥
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 加密
     * @throws Exception
     */
    public static byte[] encryptAES(byte[] data, byte[] key) throws Exception{
        //恢复密钥
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        //Cipher完成加密
        Cipher cipher = Cipher.getInstance("AES");
        //根据密钥对cipher进行初始化
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //加密
        byte[] encrypt = cipher.doFinal(data);

        return encrypt;
    }
    /**
     * 解密
     */
    public static byte[] decryptAES(byte[] data, byte[] key) throws Exception{
        //恢复密钥生成器
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        //Cipher完成解密
        Cipher cipher = Cipher.getInstance("AES");
        //根据密钥对cipher进行初始化
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plain = cipher.doFinal(data);
        return plain;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new String(new BASE64Encoder().encode(initKey())));
        System.out.println(new String(initKey(), StandardCharsets.UTF_8));
        System.out.println(new String(initKey(), StandardCharsets.UTF_8));
    }
}