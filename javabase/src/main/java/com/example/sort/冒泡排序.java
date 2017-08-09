package com.example.sort;

/**
 * Created by ifei on 2017/8/4.
 * 背景介绍：
 * 是一种简单的排序算法。
 * 它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 * ----- 来自 wikipedia
 *
 *
 * 冒泡排序是最简单的排序之一了，其大体思想就是通过与相邻元素的比较和交换来把小的数交换到最前面。
 *
 * 这个过程类似于水泡向上升一样，因此而得名。
 *
 * 举个栗子，对5,3,8,6,4这个无序序列进行冒泡排序。
 *
 * 首先从后向前冒泡，4和6比较，把4交换到前面，序列变成5,3,8,4,6。
 *
 * 同理4和8交换，变成5,3,4,8,6,3和4无需交换。5和3交换，变成3,5,4,8,6,3.
 *
 * 这样一次冒泡就完了，把最小的数3排到最前面了。
 *
 * 对剩下的序列依次冒泡就会得到一个有序序列。冒泡排序的时间复杂度为O(n^2)。
 */

public class 冒泡排序 extends SortBase{

    public static void main(String args[]){
        int[] arrays = new int[]{4,2,1,9,6,8};

        System.out.println("排序1前---------");
        print(arrays);

        sort1(arrays);

        System.out.println("排序1后---------");
        print(arrays);

        int[] arrays2 = new int[]{4,2,1,9,6,8};

        System.out.println("排序2前---------");
        print(arrays2);

        sort1(arrays2);

        System.out.println("排序2后---------");
        print(arrays2);

    }

    public static void sort1(int[] arrays){
        //assert array not null
        if(arrays == null) return ;

        int temp;

        //外层循环排序轮数 N-1
        for(int i=0;i<arrays.length-1;i++){
            //内层 N-1-i
            for(int j=0;j<arrays.length-1-i;j++){
                //两两比较,">"控制升序还是降序，此处为升序
                if(arrays[j]>arrays[j+1]){
                    temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                }
            }
        }
    }

    /**
     * 算法规则：
     * 由于算法每次都将一个最大的元素往上冒，
     * 我们可以将待排序集合(0...n)看成两部分，
     * 一部分为(k..n)的待排序unsorted集合，
     * 另一部分为(0...k)的已排序sorted集合，每一次都在unsorted集合从前往后遍历，选出一个数，
     * 如果这个数比其后面的数大，则进行交换。
     * 完成一轮之后，就肯定能将这一轮unsorted集合中最大的数移动到集合的最后，
     * 并且将这个数从unsorted中删除，移入sorted中。
     */
    public static void sort2(int[] arrays){
        if(arrays == null)return;

        int temp = 0;

        for(int i=arrays.length - 1;i>0;i++){
            for(int j=0;j<i;j++){
                if(arrays[j]>arrays[j+1]){
                    temp = arrays[j];
                    arrays[j]=arrays[j+1];
                    arrays[j+1] = temp;
                }
            }
        }
    }

}
