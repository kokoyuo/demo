package com.kokoyuo.text.jc.demo.logical;

import java.util.Objects;

/**
 * @author lixuanwen
 * @date 2020-11-13 17:17
 */
public class PointMergeOperate implements MergeOperate<Point, Point> {

    @Override
    public boolean canMerge(Point point, Point point2) {
        return ShapeOrUtil.canMerge(point, point2);
    }

    @Override
    public Shape orMerge(Point point, Point point2) {
        Point shape = new Point();
        if (canMerge(point, point2)){
            shape.setEq(point.getEq());
            return shape;
        }
        throw new RuntimeException("合并失败");
    }

    @Override
    public Shape interMerge(Point point, Point point2) {
        if (Objects.equals(point, point2)){
            Point shape = new Point();
            shape.setEq(point.getEq());
            return shape;
        }
        Line shape = new Line();
        shape.setQuickValue(false);
        return shape;
    }
}
