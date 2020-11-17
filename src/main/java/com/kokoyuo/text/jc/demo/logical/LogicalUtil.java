package com.kokoyuo.text.jc.demo.logical;

import java.util.List;

/**
 * @author lixuanwen
 * @date 2020-11-06 18:13
 */
public class LogicalUtil {

    public static void showExpress(LogicalExpress l){
        if (l == null || l.getLogicalExpress() == null || l.getLogicalExpress().size() == 0){
            return;
        }
        StringBuilder sb = new StringBuilder();
        List<List<SingleRuleLogical>> logicalExpress = l.getLogicalExpress();
        for (int i = 0; i < logicalExpress.size(); i++) {
            sb.append("(");
            List<SingleRuleLogical> logicalOperates = logicalExpress.get(i);
            if (logicalOperates != null && logicalOperates.size() > 0){
                for (int j = 0; j < logicalOperates.size(); j++) {
                    SingleRuleLogical logic = logicalOperates.get(j);
                    //sb.append("X").append(getOperateChara(logic.getSymbol()));
                    //sb.append(logic.getValue());
                    List<Shape> shapes = logic.getShapes();
                    sb.append("(");
                    for (int k = 0; k < shapes.size(); k++) {
                        Shape shape = shapes.get(k);
                        if (shape.QuickValueOpl().isPresent()){
                            if (shape.QuickValueOpl().get()){
                                sb.append("true");
                            } else {
                                sb.append("false");
                            }
                        } else {
                            if (shape instanceof Point){
                                sb.append("X=").append(((Point) shape).getEq());
                            } else if (shape instanceof Line){
                                Integer start = ((Line) shape).getStart();
                                Integer end = ((Line) shape).getEnd();
                                if (start != null && end != null){
                                    sb.append(start).append("<X<").append(end);
                                } else {
                                    if (start != null){
                                        sb.append("X>").append(start);
                                    } else {
                                        sb.append("X<").append(end);
                                    }
                                }
                            }
                        }
                        if (k != shapes.size() - 1){
                            sb.append("|");
                        }
                    }
                    sb.append(")");
                    if (j != logicalOperates.size() -1){
                        sb.append("|");
                    }
                }
            }
            sb.append(")");
            if (i != logicalExpress.size() - 1){
                sb.append("&");
            }
        }
        System.out.println(sb);
    }

    public static String getOperateChara(int i){
        return i == 0? "=":(i>0?">":"<");
    }
}
