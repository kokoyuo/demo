package com.kokoyuo.text.jc.demo.logical;

import java.util.Optional;

/**
 * @author lixuanwen
 * @date 2020-11-09 15:31
 */
public class LineMergeOperate implements MergeOperate<Line, Line> {

    @Override
    public Shape orMerge(Line line, Line line2) {
        Line shape = new Line();
        if (line.QuickValueOpl().isPresent() || line2.QuickValueOpl().isPresent()){
            // todo 完善 空集及全集的合并
        }
        Optional<Integer> lStartOp = Optional.ofNullable(line.getStart());
        Optional<Integer> lEndOp = Optional.ofNullable(line.getEnd());
        Optional<Integer> rStartOp = Optional.ofNullable(line2.getStart());
        Optional<Integer> rEndOp = Optional.ofNullable(line2.getEnd());
        // 如果是同方向的射线
        if (!lStartOp.isPresent() && !rStartOp.isPresent()) {
            shape.setEnd(Math.max(lEndOp.get(), rEndOp.get()));
            return shape;
        }
        if (!lEndOp.isPresent() && !rEndOp.isPresent()){
            shape.setStart(Math.min(lStartOp.get(), rStartOp.get()));
            return shape;
        }
        // 如果是不同方向的射线
        if (!lStartOp.isPresent() && !rEndOp.isPresent()){
            if (lEndOp.get() > lStartOp.get()){
                // 全集
                shape.setQuickValue(true);
                return shape;
            } else {
                throw new RuntimeException("无法合并");
            }
        }
        if (!lEndOp.isPresent() && !rStartOp.isPresent()){
            if (lStartOp.get() < rEndOp.get()){
                // 全集
                shape.setQuickValue(true);
                return shape;
            } else {
                throw new RuntimeException("无法合并");
            }
        }
        // 如果是一条射线和一条线段
        if (!lStartOp.isPresent()){
            if(lEndOp.get() > rStartOp.get()){
                shape.setEnd(Math.max(lEndOp.get(), rEndOp.get()));
                return shape;
            }
            throw new RuntimeException("无法合并");
        }
        if (!lEndOp.isPresent()){
            if(lStartOp.get() < rEndOp.get()){
                shape.setStart(Math.min(lStartOp.get(), rStartOp.get()));
                return shape;
            }
            throw new RuntimeException("无法合并");
        }
        if (!rStartOp.isPresent() || !rEndOp.isPresent()){
            return orMerge(line2, line);
        }
        // 如果是两条线段
        if (ShapeOrUtil.lineIsOverlap(line, line2)){
            // 如果相交
            shape.setStart(Math.min(lStartOp.get(), rStartOp.get()));
            shape.setEnd(Math.max(lEndOp.get(), rEndOp.get()));
            return shape;
        } else {
            throw new RuntimeException("无法合并");
        }
    }

    @Override
    public Shape interMerge(Line line, Line line2) {
        Optional<Integer> lStartOp = Optional.ofNullable(line.getStart());
        Optional<Integer> lEndOp = Optional.ofNullable(line.getEnd());
        Optional<Integer> rStartOp = Optional.ofNullable(line2.getStart());
        Optional<Integer> rEndOp = Optional.ofNullable(line2.getEnd());

        Line shape = new Line();
        // 如果是同方向的射线
        if (!lStartOp.isPresent() && !rStartOp.isPresent()) {
            shape.setEnd(Math.min(lEndOp.get(), rEndOp.get()));
            return shape;
        }
        if (!lEndOp.isPresent() && !rEndOp.isPresent()){
            shape.setStart(Math.max(lStartOp.get(), rStartOp.get()));
            return shape;
        }
        // 如果是不同方向的射线
        if (!lStartOp.isPresent() && !rEndOp.isPresent()){
            if (lEndOp.get() > rStartOp.get()){
                // 全集
                shape.setStart(rStartOp.get());
                shape.setEnd(lEndOp.get());
                return shape;
            } else {
                // 返回空集
                return generateNewEmptyShape();
            }
        }
        if (!lEndOp.isPresent() && !rStartOp.isPresent()){
            if (lStartOp.get() < rEndOp.get()){
                // 全集
                shape.setStart(lStartOp.get());
                shape.setEnd(rEndOp.get());
                return shape;
            } else {
                // 返回空集
                return generateNewEmptyShape();
            }
        }
        // 如果是一条射线和一条线段
        if (!lStartOp.isPresent()){
            if(lEndOp.get() > rStartOp.get()){
                shape.setStart(rStartOp.get());
                shape.setEnd(Math.min(lEndOp.get(), rEndOp.get()));
                return shape;
            }
            // 返回空集
            return generateNewEmptyShape();
        }
        if (!lEndOp.isPresent()){
            if(lStartOp.get() < rEndOp.get()){
                shape.setStart(Math.max(lStartOp.get(), rStartOp.get()));
                shape.setEnd(rEndOp.get());
                return shape;
            }
            // 返回空集
            return generateNewEmptyShape();
        }
        if (!rStartOp.isPresent() || !rEndOp.isPresent()){
            return interMerge(line2, line);
        }
        // 如果是两条线段
        if (ShapeOrUtil.lineIsOverlap(line, line2)){
            // 如果相交
            shape.setStart(Math.max(lStartOp.get(), rStartOp.get()));
            shape.setEnd(Math.min(lEndOp.get(), rEndOp.get()));
            return shape;
        } else {
            return generateNewEmptyShape();
        }
    }

    private Line generateNewEmptyShape(){
        Line l = new Line();
        l.setQuickValue(false);
        return l;
    }
}
