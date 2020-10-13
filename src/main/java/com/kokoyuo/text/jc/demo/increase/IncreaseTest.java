package com.kokoyuo.text.jc.demo.increase;

import com.alibaba.fastjson.JSONObject;
import com.kokoyuo.text.jc.demo.lambda.exception.MallItemException;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

/**
 * @author lixuanwen
 * @date 2020-06-17 14:39
 */
public class IncreaseTest {

    public static LinkedHashSet<Character> characterSet = new LinkedHashSet<>(24);

    public static Integer taskMaxNum = 1000;
    static {
        for (char i = 'A'; i <= 'Z'; i++) {
            if (!(Objects.equals(i, 'O') || Objects.equals(i, 'I'))){
                characterSet.add(i);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(characterSet));
        String maxCardSign = findMaxCardSign();
        System.out.println("maxCardSign:" + maxCardSign);

        splitTask(3000, 1000);

        Thread a = new Thread();
        a.start();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        };
    }

    public static String findMaxCardSign(){
        String cardSign = "A109";
        if (StringUtils.isEmpty(cardSign)){
            return "A000";
        }
        if (cardSign.compareTo("A000") < 0){
            return "A000";
        }
        String prefixLetter = cardSign.substring(0, 1);
        String postfix = cardSign.substring(1);
        int numPostfix = Integer.parseInt(postfix);
        String targetNumPostfix = StringUtils.leftPad(String.valueOf(numPostfix + 1), 3, "0");
        // 假如发生了进位
        if (String.valueOf(targetNumPostfix).length() > 3){
            Character nextLetter = findNextLetter(prefixLetter.charAt(0)).orElseThrow(() ->  new MallItemException(""));
            return StringUtils.rightPad(String.valueOf(nextLetter), 4, "0");
        }
        return prefixLetter + targetNumPostfix;
    }

    public static Optional<Character> findNextLetter(Character c){
        if (!characterSet.contains(c)){
            return Optional.empty();
        }
        for (Iterator<Character> iterator = characterSet.iterator();iterator.hasNext();) {
            Character temp = iterator.next();
            if (Objects.equals(c, temp)){
                if (iterator.hasNext()){
                    return Optional.of(iterator.next());
                } else {
                    // 超过了最大范围
                    return Optional.empty();
                }
            }
        }
        return Optional.empty();
    }

    public static void splitTask(Integer maxNum, Integer minNum){
        Integer taskNum = (maxNum - minNum) / taskMaxNum;
        Integer remainder = (maxNum - minNum) % taskMaxNum;
        for (int i = 0; i <= taskNum; i++) {
            Integer tempTaskStart = null;
            Integer tempTaskEnd = null;
            if (i == taskNum){
                tempTaskStart = minNum + (i * taskMaxNum);
                tempTaskEnd = minNum + (i * taskMaxNum) + remainder;
            } else {
                tempTaskStart = minNum + (i * taskMaxNum);
                tempTaskEnd = minNum + ((i + 1) * taskMaxNum) - 1;
            }
            System.out.println(tempTaskStart+"-"+tempTaskEnd);
        }
    }
}
