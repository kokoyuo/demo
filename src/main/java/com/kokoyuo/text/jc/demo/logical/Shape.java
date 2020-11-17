package com.kokoyuo.text.jc.demo.logical;

import java.util.Optional;

/**
 * @author lixuanwen
 * @date 2020-11-09 10:30
 */
public interface Shape {

    int getShapeType();

    /**
     * 是否能够合并
     * @return
     */
    default boolean mergeAble(Shape shape){
        return false;
    }

    default Shape orMerge(Shape shape){
        throw new RuntimeException("无法合并");
    }

    default Shape interMerge(Shape shape){
        throw new RuntimeException("无法合并");
    }

    /**
     * 处理全集和空集
     * @param shape
     * @return
     */
    default Shape orMergeQuickValue(Shape shape){
        if (!this.QuickValueOpl().isPresent() && !shape.QuickValueOpl().isPresent()){
            // 返回空表示无法通过 快速标识合并
            return null;
        }
        if (shape.QuickValueOpl().isPresent() && !this.QuickValueOpl().isPresent()){
            Boolean shapeFlag = shape.QuickValueOpl().get();
            if (shapeFlag){
                // 表示全集求并集 为全集
                return generateNewUniverseShape();
            }
            return this;
        }
        if (shape.QuickValueOpl().isPresent() && this.QuickValueOpl().isPresent()){
            Boolean shapeFlag = shape.QuickValueOpl().get();
            Boolean thisFlag = this.QuickValueOpl().get();
            if (shapeFlag || thisFlag){
                // 表示全集求并集 为全集
                return generateNewUniverseShape();
            }
            return generateNewEmptyShape();
        }
        return shape.orMergeQuickValue(this);
    }

    default Shape interMergeQuickValue(Shape shape){
        if (!this.QuickValueOpl().isPresent() && !shape.QuickValueOpl().isPresent()){
            // 返回空表示无法通过 快速标识合并
            return null;
        }
        if (shape.QuickValueOpl().isPresent() && !this.QuickValueOpl().isPresent()){
            Boolean shapeFlag = shape.QuickValueOpl().get();
            if (shapeFlag){
                // 表示全集求并集 为全集
                return this;
            }
            return generateNewEmptyShape();
        }
        if (shape.QuickValueOpl().isPresent() && this.QuickValueOpl().isPresent()){
            Boolean shapeFlag = shape.QuickValueOpl().get();
            Boolean thisFlag = this.QuickValueOpl().get();
            if (shapeFlag && thisFlag){
                // 表示全集求并集 为全集
                return generateNewUniverseShape();
            }
            return generateNewEmptyShape();
        }
        return shape.interMergeQuickValue(this);
    }

    /**
     * true 表示全集
     * false 表示空集
     * @return
     */
    default Optional<Boolean> QuickValueOpl(){
        return Optional.empty();
    }

    default Shape generateNewEmptyShape(){
        Line l = new Line();
        l.setQuickValue(false);
        return l;
    }

    default Shape generateNewUniverseShape(){
        Line l = new Line();
        l.setQuickValue(true);
        return l;
    }
}
