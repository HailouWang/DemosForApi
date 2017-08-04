package com.example.linknode;

/**
 * Created by ifei on 2017/8/1.
 * LinkedList:双向链表
 * ArrayList:数组
 *
 * 链表的add方法，不允许越界。即：如果只有两个元素，是不能add(8,Object)的，会返回越界错误。
 */

public class 单链表 {

    class SingleLinkList{
        public Node first;//头节点
        private int pos = 0;//节点位置

        /**
         * 添加头节点
         * @param data
         */
        public void addFirstNode(int data){
            Node node = new Node(data);
            node.next = first;
            first = node;
        }

        /**
         * 删除头节点
         */
        public void deleteFirstNode(){
            Node tempNode = first;
            first = tempNode.next;
        }

        /**
         * 添加节点
         * @param index
         * @param data
         * @return
         */
        public Node addNode(int index,int data){
            /**
             * 1、构造当前节点信息
             */
            Node node = new Node(data);

            /**
             * 2、从头节点开始遍历，故：先得到头节点
             */
            Node current = first;
            Node previous = first;

            /**
             * 3、遍历得到index的位置，并得到index前、后节点
             */
            while(pos!=index){
                previous = current;
                current = current.next;
                pos++;
            }

            /**
             * 4、加入链表
             */
            node.next = current;
            previous.next = node;
            pos = 0;

            return node;
        }

        public Node removeNode(int index){
            Node current = first;
            Node previous = first;
            while(pos!=index){
                previous = current;
                current = current.next;
                pos++;
            }
            if(first == current){
                first = current.next;
            }else{
                previous.next = current.next;
            }
            pos = 0;
            return current;
        }
    }
}
