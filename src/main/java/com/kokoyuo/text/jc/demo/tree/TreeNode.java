package com.kokoyuo.text.jc.demo.tree;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lixuanwen
 * @date 2020-06-15 09:51
 */
public interface TreeNode<K, V> {

    int comparatorData(V v1, V v2);

    List<TreeNode<K, V>> getChild();

    V getData();

    K generateId();

    default int comparator(TreeNode<K, V> t1, TreeNode<K, V> t2){
        return comparatorData(t1.getData(), t2.getData());
    }
}
