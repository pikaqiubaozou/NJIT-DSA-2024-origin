package oy.tol.tra;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private TreeNode<K, V> root = null;
    private int count = 0;
    private int maxTreeDepth = 0;

    @Override
    public Type getType() {
        return Type.BST;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String getStatus() {
        StringBuilder statusBuilder = new StringBuilder();
        statusBuilder.append("Tree has max depth of " + maxTreeDepth + ".\n");
        statusBuilder.append("Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n");

        // 使用 TreeAnalyzerVisitor 访问树节点并获取信息
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);

        // 添加关于树高度的信息
        statusBuilder.append("Min path height to bottom: " + visitor.minHeight + "\n");
        statusBuilder.append("Max path height to bottom: " + visitor.maxHeight + "\n");
        statusBuilder.append("Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n");

        return statusBuilder.toString();
    }

    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("This is a custom NullPointerException message");
        }
        if (root == null) {
            // 如果树为空，则创建根节点
            root = new TreeNode<>(key, value);
            count++;
            return true;
        } else {
            // 否则在树中插入新的键值对
            count += root.insert(key, value, key.hashCode());
            return true;
        }
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // 由于树的动态性质，不需要确保容量
    }

    public Pair<K, V>[] toSortedArray() {
        // 使用 TreeToArrayVisitor 将树转换为有序数组
        TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(count);
        root.accept(visitor);
        Pair<K, V>[] sorted = visitor.getArray();
        // 对数组进行排序
        Algorithms.mergeSort(sorted);
        return sorted;
    }

    public V find(K key) {
        // 在树中查找指定的键
        return root.find(key, key.hashCode());
    }

    @Override
    public void compress() throws OutOfMemoryError {
        // 由于树的自平衡性质，不需要压缩操作
    }
}