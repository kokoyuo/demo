package com.kokoyuo.text.jc.demo.logical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author lixuanwen
 * @date 2020-11-06 18:08
 */
public class LogicalTest {

    public static void main(String[] args) {
        Line line1 = new Line(null, 0, 2);
        Line line2 = new Line(null, 1, null);
        Line line3 = new Line(null, -5, -4);
        // orMerge(line1, line2);
        // interMerge(line1, line2);

        // List<Shape> shapes = orMergeList(Arrays.asList(line1, line3), Arrays.asList(line2));
        // List<Shape> shapes = interMergeList(Arrays.asList(line1, line3), Arrays.asList(line2));
        testLogical();
    }

    public static void testLogical() {
        LogicalExpress express = new LogicalExpress();
        express.setLogicalExpress(Arrays.asList(generateLeftExpress(), generateRightExpress()));
        LogicalUtil.showExpress(express);
        // 化简
        List<List<SingleRuleLogical>> logicalExpress = express.getLogicalExpress();
        // 交集
        List<Shape> shapes = logicalExpress.stream()
                .map(singleRuleLogicals -> {
                    // 并集
                    Optional<List<Shape>> reduce = singleRuleLogicals.stream()
                            .map(SingleRuleLogical::getShapes)
                            .reduce(LogicalTest::orMergeList);
                    return reduce.orElse(new ArrayList<>());
                }).reduce(LogicalTest::interMergeList)
                .orElse(new ArrayList<>());
        System.out.println(shapes);
    }

    public static List<SingleRuleLogical> generateLeftExpress(){
        SingleRuleLogical a = new SingleRuleLogical();
        Line s1 = new Line(1, 3);
        Line s2 = new Line(5, 7);
        a.setShapes(Arrays.asList(s1, s2));

        SingleRuleLogical b = new SingleRuleLogical();
        b.setShapes(Arrays.asList(new Line(9, 11), new Line(13, 100)));
        return Arrays.asList(a, b);
    }

    public static List<SingleRuleLogical> generateRightExpress(){
        SingleRuleLogical a = new SingleRuleLogical();
        Line s1 = new Line(11, 13);
        Line s2 = new Line(15, 17);
        a.setShapes(Arrays.asList(s1, s2));

        SingleRuleLogical b = new SingleRuleLogical();
        b.setShapes(Arrays.asList(new Line(19, 21), new Line(43, 100)));
        return Arrays.asList(a, b);
    }

    public static Shape orMerge(Shape s1, Shape s2){
        if (!s1.mergeAble(s2)){
            System.out.println("不可以合并");
            return null;
        }
        Shape merge = s1.orMerge(s2);
        System.out.println(merge);
        return merge;
    }

    public static Shape interMerge(Shape s1, Shape s2){
        Shape shape = s1.interMerge(s2);
        System.out.println(shape);
        return shape;
    }

    public static void t2(){
        LogicalExpress logic = new LogicalExpress();
        List<List<SingleRuleLogical>> logicalExpress = new ArrayList<>();
        List<SingleRuleLogical> orLogic = new ArrayList<>();
        //orLogic.add(SingleRuleLogical.builder().shapes(Arrays.asList(new Line(1, 3), new Point(5))).build());
        //orLogic.add(SingleRuleLogical.builder().shapes(Arrays.asList(new Line(1, 3), new Point(5))).build());

        List<SingleRuleLogical> orLogic2 = new ArrayList<>();
        /*orLogic2.add(SingleRuleLogical.builder().symbol(1).value(6).build());
        orLogic2.add(SingleRuleLogical.builder().symbol(0).value(4).build());
        orLogic2.add(SingleRuleLogical.builder().symbol(-1).value(1).build());*/

        logicalExpress.add(orLogic);
        logicalExpress.add(orLogic2);

        logic.setLogicalExpress(logicalExpress);

        LogicalUtil.showExpress(logic);
    }

    private static List<Shape> orMergeList(List<Shape> s1, List<Shape> s2){
        List<Shape> shapes = ShapeCollectionUtil.orMerge(s1, s2);
        System.out.println(shapes);
        return shapes;
    }

    private static List<Shape> interMergeList(List<Shape> s1, List<Shape> s2){
        List<Shape> shapes = ShapeCollectionUtil.interMerge(s1, s2);
        System.out.println(shapes);
        return shapes;
    }
}
