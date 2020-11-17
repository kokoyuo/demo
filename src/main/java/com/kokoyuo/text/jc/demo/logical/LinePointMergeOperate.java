package com.kokoyuo.text.jc.demo.logical;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

/**
 * @author lixuanwen
 * @date 2020-11-09 15:24
 */
public class LinePointMergeOperate implements MergeOperate<Line, Point> {

    @Override
    public Shape orMerge(Line line, Point point) {
        if(canMerge(line, point)){
            Line shape = new Line(null, line.getStart(), line.getEnd());
            return shape;
        }
        return line;
    }

    @Override
    public boolean canMerge(Line line, Point point) {
        return ShapeOrUtil.canMerge(point, line);
    }

    @Override
    public Shape interMerge(Line line, Point point) {
        Optional<Integer> lStart = Optional.ofNullable(line.getStart());
        Optional<Integer> lEnd = Optional.ofNullable(line.getEnd());
        Integer eq = point.getEq();
        Line shapeEmpty = new Line();
        shapeEmpty.setQuickValue(false);
        if (lStart.isPresent()){
            if (lStart.get() >= eq){
                return shapeEmpty;
            }
        }
        if (lEnd.isPresent()){
            if (lEnd.get() <= eq){
                return shapeEmpty;
            }
        }
        Point result = new Point();
        result.setEq(eq);
        return result;
    }
}
