package com.kokoyuo.text.jc.demo.logical;

import lombok.*;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * @author lixuanwen
 * @date 2020-11-06 23:16
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Line implements Shape {

    private Boolean quickValue;
    /**
     * -1,0,1
     */
    private Integer start;

    private Integer end;

    public Line(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int getShapeType() {
        return 1;
    }

    /**
     * 是否能够合并
     *
     * @param shape
     * @return
     */
    @Override
    public boolean mergeAble(Shape shape) {
        if (shape instanceof Point){
            return ShapeOrUtil.canMerge((Point) shape, this);
        } else if (shape instanceof Line){
            return ShapeOrUtil.canMerge((Line) shape, this);
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
            return new LinePointMergeOperate().orMerge(this, (Point) shape);
        }
        if (shape instanceof Line){
            return new LineMergeOperate().orMerge((Line) shape, this);
        }
        throw new RuntimeException("无法合并");
    }

    @Override
    public Shape interMerge(Shape shape) {
        Shape quickShape = interMergeQuickValue(shape);
        if (quickShape != null){
            return quickShape;
        }
        if (shape instanceof Point){
            return new LinePointMergeOperate().interMerge(this, (Point) shape);
        }
        if (shape instanceof Line){
            return new LineMergeOperate().interMerge((Line) shape, this);
        }
        throw new RuntimeException("无法合并");
    }

    /**
     * true 表示全集
     * false 表示空集
     *
     * @return
     */
    @Override
    public Optional<Boolean> QuickValueOpl() {
        return Optional.ofNullable(quickValue);
    }

}
