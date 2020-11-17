package com.kokoyuo.text.jc.demo.logical;

import java.util.Objects;
import java.util.Optional;

/**
 * @author lixuanwen
 * @date 2020-11-09 13:46
 */
public class ShapeOrUtil {

    /**
     * 点和线合并 或
     * @param p
     * @param e
     * @return
     */
    public static boolean canMerge(Point p, Line e){
        Optional<Integer> startOp = Optional.ofNullable(e)
                .map(Line::getStart);
        if (startOp.isPresent()){
            // 如果点在线段起点左侧
            if (p.getEq() <= startOp.get()){
                return false;
            }
        }
        Optional<Integer> endOp = Optional.ofNullable(e)
                .map(Line::getEnd);
        if (endOp.isPresent()){
            // 如果点在线段终点右侧
            if (p.getEq() <= startOp.get()){
                return false;
            }
        }
        return true;
    }

    /**
     * 点和点合并
     * @param p
     * @param p2
     * @return
     */
    public static boolean canMerge(Point p, Point p2){
        return Objects.equals(p, p2);
    }

    /**
     * 线和线之间的合并
     * 不相交状况的否
     * @param e
     * @param e2
     * @return
     */
    public static boolean canMerge(Line e, Line e2){
        if (e.QuickValueOpl().isPresent() || e2.QuickValueOpl().isPresent()){
            return true;
        }
        Optional<Integer> leftStartOp = Optional.ofNullable(e.getStart());
        Optional<Integer> leftEndOp = Optional.ofNullable(e.getEnd());
        Optional<Integer> rStartOp = Optional.ofNullable(e2.getStart());
        Optional<Integer> rEndOp = Optional.ofNullable(e2.getEnd());
        if (Objects.equals(e, e2)){
            return true;
        }
        // 相同方向的射线都会相交
        if (!leftStartOp.isPresent() && !rStartOp.isPresent()){
            return true;
        }
        if (!leftEndOp.isPresent() && !rEndOp.isPresent()){
            return true;
        }
        // 不同方向的射线
        if (!leftStartOp.isPresent() && !rEndOp.isPresent()){
            return leftEndOp.get() > rStartOp.get();
        }
        if (!leftEndOp.isPresent() && !rStartOp.isPresent()){
            return leftStartOp.get() < rEndOp.get();
        }
        // 射线和线段
        if (!leftStartOp.isPresent()){
            return rStartOp.get() < leftEndOp.get() || rEndOp.get() < leftEndOp.get();
        }
        if (!leftEndOp.isPresent()){
            return rStartOp.get() > leftStartOp.get() || rEndOp.get() > leftStartOp.get();
        }
        if (!rEndOp.isPresent() || !rStartOp.isPresent()){
            return canMerge(e2, e);
        }
        // 线段和线段
        if (leftEndOp.isPresent() && leftStartOp.isPresent() && rStartOp.isPresent() && rEndOp.isPresent()){
            return (rStartOp.get() > leftStartOp.get() && rStartOp.get() < leftEndOp.get())
                    || (rEndOp.get() > leftStartOp.get() && rEndOp.get() < leftEndOp.get()
                    || (rStartOp.get() <= leftStartOp.get() && rEndOp.get() >= leftEndOp.get()));
        }
        System.out.println("兜底");
        return true;
    }

    /**
     * 判断两条线段是否交叉
     * @return
     */
    public static boolean lineIsOverlap(Line l, Line r){
        boolean flag = (r.getStart() > l.getStart() && r.getStart() < l.getEnd())
                || (r.getEnd() > l.getStart() && r.getEnd() < l.getEnd())
                || (r.getStart() <= l.getStart() && r.getEnd() >= l.getEnd());
        return flag;
    }
}
