package com.kokoyuo.text.jc.demo.logical;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author lixuanwen
 * @date 2020-11-09 10:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Point implements Shape{

    private Integer eq;

    @Override
    public int getShapeType() {
        return 0;
    }

    /**
     * 是否能够合并
     *
     * @return
     */
    @Override
    public boolean mergeAble(Shape shape) {
        if (shape instanceof Point){
            return ShapeOrUtil.canMerge((Point) shape, this);
        } else if (shape instanceof Line){
            return ShapeOrUtil.canMerge(this, (Line) shape);
        }
        return false;
    }

    @Override
    public Shape orMerge(Shape shape) {
        Shape quickShape = orMergeQuickValue(shape);
        if (quickShape != null){
            return quickShape;
        }
        if (shape instanceof Point){
            return new PointMergeOperate().orMerge(this, (Point) shape);
        }
        if (shape instanceof Line){
            return new LinePointMergeOperate().orMerge((Line) shape, this);
        }
        throw new RuntimeException("无法合并");
    }

    @Override
    public Shape interMerge(Shape shape) {
        Shape quickShape = interMergeQuickValue(shape);
        if (quickShape != null){
            return quickShape;
        }
        return null;
    }
}
