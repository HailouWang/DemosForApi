package com.example.tree;

import com.example.sort.SortBase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * Created by ifei on 2017/8/8.
 *
 * 二叉树中左右节点值本身没有大小之分，
 *
 * 所以如果要创建二叉树，就需要考虑如何处理某个节点是左节点还是右节点，
 *
 * 如何终止某个子树而切换到另一个子树。 因此我选择了二叉排序树，二叉排序树中对于左右节点有明确的要求，
 *
 * 程序可以自动根据键值大小自动选择是左节点还是右节点。
 *
 *
 *
 * 二叉排序树或者是一棵空树，或者是具有下列性质的二叉树：
 （1）若左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 （2）若右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 （3）左、右子树也分别为二叉排序树；
 （4）没有键值相等的节点。

                                  8
                                /  \
                               /    \
                              /      \
                             3       10
                            / \        \
                           1  6        14
                             / \       /
                            4  7      13


 树的遍历方法一般有如下几种方法：
 （1）层次遍历：按照树的层次进行遍历，如图树：8、3、10、1、6、14、4、7、13
 （2）先序遍历：节点遍历顺序为当前节点、左节点、右节点。如图树：8、3、1、6、4、7、10、14、13
 （3）中序遍历：节点遍历顺序为左节点、当前节点、右节点。如图树：1、3、4、6、7、8、10、13、14
 （4）后续遍历：节点遍历顺序为左节点、右节点、当前节点。如图树：1、4、7、6、3、8、13、14、10


 备注：
 Queue接口：
 add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
 remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
 element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
 offer       添加一个元素并返回true       如果队列已满，则返回false
 poll         移除并返问队列头部的元素    如果队列为空，则返回null
 peek       返回队列头部的元素             如果队列为空，则返回null
 put         添加一个元素                      如果队列满，则阻塞
 take        移除并返回队列头部的元素     如果队列为空，则阻塞

 Stack接口：
 E push(E item)
 把项压入堆栈顶部。
 E pop()
 移除堆栈顶部的对象，并作为此函数的值返回该对象。
 E peek()
 查看堆栈顶部的对象，但不从堆栈中移除它。
 boolean empty()
 测试堆栈是否为空。
 int search(Object o)
 返回对象在堆栈中的位置，以 1 为基数。

 */

public class 二叉搜索树 extends SortBase{

    public static void main(String args[]){
        int[] array = new int[]{8,3,10,1,6,14,4,7,13};

        二叉搜索树 binary = new 二叉搜索树();
        binary.insert(array);

        print("层序遍历：",binary.layerOrder());
        print("先序遍历：",binary.preOrder());
        print("中序遍历：",binary.inOrder());
        print("后序遍历：",binary.poseOrder());

        ArrayList<Integer> array0 = new ArrayList<Integer>();
        binary.layerOrderByRecursive(binary.root,array0);
        print("层序递归遍历：",array0);

        ArrayList<Integer> array1 = new ArrayList<Integer>();
        binary.preOrderRe(binary.root,array1);
        print("先序递归遍历：",array1);

        ArrayList<Integer> array2 = new ArrayList<Integer>();
        binary.inOrderRe(binary.root,array2);
        print("中序递归遍历：",array2);

        ArrayList<Integer> array3 = new ArrayList<Integer>();
        binary.poseOrderRe(binary.root,array3);
        print("后序递归遍历：",array3);
    }




    TreeNode root;
    int size;

    /**
     * 插入node节点，非递归实现
     * @param node
     */
    public void insert(TreeNode node){
        if(node == null){
            return;
        }
        if(root == null){
            root = node;
            size++;
            return;
        }
        TreeNode currentNode = root;
        while(true){
            //如果node小于当前节点的值,则：如果左子树不为null
            if(node.value<currentNode.value){
                if(currentNode.leftNode==null){
                    currentNode.leftNode = node;
                    size++;
                    return;
                }
                currentNode = currentNode.leftNode;
            }else{//否则考虑右子数
                if(currentNode.rightNode == null){
                    currentNode.rightNode = node;
                    size++;
                    return;
                }
                currentNode = currentNode.rightNode;
            }
        }
    }

    /**
     * 插入值为data的节点
     * @param data
     */
    public void insert(int data){
        insert(new TreeNode(data));
    }

    /**
     * 插入节点数组
     * @param array
     */
    public void insert(int[] array){
        if(array == null) return;
        for(int i=0;i<array.length;i++){
            insert(new TreeNode(array[i]));
        }
    }

    /**
     * 层序遍历,非递归方式，广度优先
     * 使用队列，先进先出，保证层序
     * @return
     */
    public int[] layerOrder(){
        int[] array = new int[size];
        if(root == null){
            return array;
        }
        int i=0;
        //使用队列，先进先出
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode next = queue.poll();
            array[i++] = next.value;
            TreeNode left = next.leftNode;
            if(left != null){
                queue.add(left);
            }
            TreeNode right = next.rightNode;
            if(right!=null){
                queue.add(right);
            }
        }
        return array;
    }

    /**
     * 先序遍历,非递归算法
     * Stack:先进先出的特点
     * 1、保证stack将每个节点，加入Stack
     * 2、先将节点开始的左子树遍历完毕
     * 3、依次向上检查每个节点的右子数，如果存在，依次节点为准，再次执行1、2。
     * @return
     */
    public int[] preOrder(){
        int[] array = new int[size];
        if(root == null){
            return array;
        }
        int i=0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null){
            //1、先将 左子树遍历完毕,并保证将所有左子树的节点，加入stack
            while(node!=null){
                stack.push(node);
                array[i++] = node.value;
                node = node.leftNode;
            }
            //2、向上回溯遍历左子树节点，找到不为空的右子树。
            while(!stack.isEmpty()&&stack.peek().rightNode==null){
                stack.pop();
            }

            //3、以右子树为节点，开始遍历查找右子树作为节点的左子树
            if(!stack.isEmpty()){
                node = stack.pop();
                if(node!=null){
                    node = node.rightNode;
                }
            }else{
                node = null;
            }
        }
        return array;
    }

    /**
     * 中序遍历，非递归实现
     * @return
     */
    public int[] inOrder(){
        int[] array = new int[size];
        if(root == null) {
            return array;
        }

        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;

        int i = 0;
        while(node != null){
            //1、遍历将左子树，加入堆栈
            while(node!=null){
                stack.push(node);
                node = node.leftNode;
            }

            //2、如果堆栈不为null，并且节点不存在右子树，则将节点加入数组
            while(!stack.isEmpty()&&stack.peek().rightNode == null){
                node = stack.pop();
                array[i++] = node.value;
            }

            //3、找到了右子树存在的节点，那么继续遍历左子树
            if(!stack.isEmpty()){
                node = stack.pop();
                if(node != null){
                    array[i++]=node.value;
                    node = node.rightNode;
                }
            }else{
                node = null;
            }
        }
        return array;
    }

    /**
     * 后序遍历，非递归
     * @return
     */
    public int[] poseOrder(){
        int[] array = new int[size];
        if(root == null){
            return array;
        }

        Stack<TreeNode> stack = new Stack<>();
        //储存节点是否被访问过
        Stack<Boolean> stackFlag = new Stack<>();

        int i = 0;

        TreeNode node = root;
        while(node!=null){
            //1、将所有左子树加入到stack
            while(node!=null){
                stack.push(node);
                stackFlag.push(false);
                node = node.leftNode;
            }
            //2、查看是否有右子树，如果没有，则将左子树或节点加入数组，或者已经被访问过的节点接入数组
            while(!stack.isEmpty()&&(stack.peek().rightNode == null || stackFlag.peek())){
                node = stack.pop();
                stackFlag.pop();
                array[i++] = node.value;
            }
            //3、如果节点有右子树，则将节点赋值给node，并重新循环
            if(!stack.isEmpty()){
                node = stack.peek();
                stackFlag.pop();
                if(node !=null){
                    stackFlag.push(true);
                    node = node.rightNode;
                }
            }else{
                node = null;
            }
        }

        return array;
    }

    private int depth(TreeNode node){
        if(node == null)return 0;
        int left = depth(node.leftNode);
        int right = depth(node.rightNode);
        if(left>right){
            return left+1;
        }else{
            return right+1;
        }
    }

    public void layerOrderByRecursive(TreeNode node, ArrayList<Integer> list){
        if(node == null) return;
        if(list == null){
            list = new ArrayList<>();
        }

        int depth = depth(node);
        for(int i=1;i<=depth;i++){
            layerOrderByRecursiveCycle(node,list,i);
        }
    }

    private void layerOrderByRecursiveCycle(TreeNode node, ArrayList<Integer> list,int depth){
        if(node == null || list == null)return;
        if(depth == 1){
            list.add(node.value);
            return;
        }
        layerOrderByRecursiveCycle(node.leftNode,list,depth-1);
        layerOrderByRecursiveCycle(node.rightNode,list,depth-1);
    }

    public void preOrderRe(TreeNode node,ArrayList<Integer> list){
        if(node == null)return;
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(node.value);
        if(node.leftNode!=null){
            preOrderRe(node.leftNode,list);
        }
        if(node.rightNode!=null){
            preOrderRe(node.rightNode,list);
        }
    }

    public void inOrderRe(TreeNode node,ArrayList<Integer> list){
        if(node == null)return;
        if(list == null){
            list = new ArrayList<>();
        }
        if(node.leftNode!=null){
            inOrderRe(node.leftNode,list);
        }
        list.add(node.value);
        if(node.rightNode!=null){
            inOrderRe(node.rightNode,list);
        }
    }

    public void poseOrderRe(TreeNode node,ArrayList<Integer> list){
        if(node == null)return;
        if(list == null){
            list = new ArrayList<>();
        }
        if(node.leftNode!=null){
            inOrderRe(node.leftNode,list);
        }else{
            list.add(node.value);
        }
        if(node.rightNode!=null){
            inOrderRe(node.rightNode,list);
        }
        list.add(node.value);
    }
}
