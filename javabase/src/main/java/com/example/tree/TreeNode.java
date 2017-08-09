package com.example.tree;

/**
 * Created by ifei on 2017/8/8.
 * 二叉树节点定义
 *
 * 采用单项链表的形式，只从根节点指向孩子节点，不保存父节点。
 *
 */

public class TreeNode {
    TreeNode leftNode;
    TreeNode rightNode;
    int value;

    public TreeNode(int data){
        value = data;
    }

    public TreeNode(){
        this(0);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                ", value=" + value +
                '}';
    }
}
