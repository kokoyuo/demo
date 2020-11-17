package com.kokoyuo.text.jc.demo.logical;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lixuanwen
 * @date 2020-11-17 10:34
 */
public class ShapeCollectionUtil {

    public static List<Shape> orMerge(List<Shape> s1, List<Shape> s2){
        LinkedList<Shape> collect = new LinkedList<>(s1);
        collect.addAll(s2);
        boolean flag = true;
        while(flag){
            flag = false;
            for (int i = 0; i < collect.size() - 1; i++) {
                Shape ls = collect.get(i);
                for (int j = i + 1; j < collect.size(); j++) {
                    Shape rs = collect.get(j);
                    if (ls.mergeAble(rs)){
                        Shape temp = ls.orMerge(rs);
                        collect.set(i, temp);
                        collect.remove(j);
                        flag = true;
                        break;
                    }
                }
            }
        }
        return collect;
    }

    public static List<Shape> interMerge(List<Shape> s1, List<Shape> s2){
        List<Shape> interResult = new ArrayList<>();
        for (int i = 0; i < s1.size(); i++) {
            Shape l = s1.get(i);
            for (int j = 0; j < s2.size(); j++) {
                Shape r = s2.get(j);
                interResult.add(l.interMerge(r));
            }
        }
        List<Shape> shapes = orMerge(interResult, new ArrayList<>());
        return shapes;
    }
}
