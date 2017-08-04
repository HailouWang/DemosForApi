package com.example.sort;

/**
 * Created by ifei on 2017/8/4.
 *
 *
 * 背景介绍：
 * 选择排序（Selection sort）是一种简单直观的排序算法。
 * 它的工作原理如下。
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，
 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * ----- 来自 wikipedia
 */

public class 选择排序 {

    public static void main(String args[]) {
        int[] arrays = new int[]{1, 4, 7, 0, 6};

        System.out.println("排序前---------");
        print(arrays);

        sort(arrays);

        System.out.println("排序后---------");
        print(arrays);
    }

    /**
     * 将待排序集合(0...n)看成两部分，在起始状态中，
     * 一部分为(k..n)的待排序unsorted集合，
     * 另一部分为(0...k)的已排序sorted集合,
     *
     * 在待排序集合中挑选出最小元素并且记录下标i，
     *
     * 若该下标不等于k，那么 unsorted[i] 与 sorted[k]交换 ，一直重复这个过程，直到unsorted集合中元素为空为止。
     * @param arrays
     */
    public static void sort(int[] arrays){
        //assert arrays not null
        if(arrays == null)return;
        int temp = 0;
        for(int i=0,k=0;i<arrays.length;i++,k=i){
            for(int j=k;j<arrays.length;j++){
                if(arrays[k]>arrays[j]){//">"决定排序顺序，此处是升序。"<"是降序
                    k = j;
                }
            }
            if(i!=k){
                temp = arrays[i];
                arrays[i] = arrays[k];
                arrays[k] = temp;
            }
        }
    }

    public static void print(int[] arrasy){
        if(arrasy==null)return;
        for(int i=0;i<arrasy.length;i++){
            System.out.print("    "+arrasy[i]);
        }
        System.out.println();
    }
}
