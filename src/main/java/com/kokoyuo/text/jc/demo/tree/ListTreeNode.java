package com.kokoyuo.text.jc.demo.tree;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * @author lixuanwen
 * @date 2020-06-15 10:11
 */
public class ListTreeNode<K, V> implements TreeNode<K, V>{

    private V data;

    @Ignore
    private K id;


    private List<TreeNode<K, V>> child;

    private Comparator<V> dataComparator;

    private Function<V, K> idGenerate;

    // private

    public ListTreeNode(List<V> source, Comparator<V> dataComparator, Function<V, K> idGenerate) {
        this.dataComparator = dataComparator;
        this.idGenerate = idGenerate;
        data = null;
        child = null;
        id = idGenerate.apply(data);
    }

    @Override
    public int comparatorData(V v1, V v2) {
        return dataComparator.compare(v1, v2);
    }

    @Override
    public List<TreeNode<K, V>> getChild() {
        return child;
    }

    @Override
    public V getData() {
        return data;
    }

    @Override
    public K generateId() {
        return id;
    }

    public static <K, V> ListTreeNode<K, V> initListTreeNode(List<V> v , Comparator<V> dataComparator, Function<V, K> idGenerate){
        return new ListTreeNode<>(v, dataComparator, idGenerate);
    }

    public static void main(String[] args) {
        ListTreeNode<String, String> listTreeNode = initListTreeNode(Arrays.asList("1", "2"), String::compareTo, s -> s);
        // listTreeNode.
    }
}
