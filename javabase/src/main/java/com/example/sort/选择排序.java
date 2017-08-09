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
 *
 *
 * 选择排序的思想其实和冒泡排序有点类似，都是在一次排序后把最小的元素放到最前面。
 *
 * 但是过程不同，冒泡排序是通过相邻的比较和交换。
 *
 * 而选择排序是通过对整体的选择。
 *
 * 举个栗子，对5,3,8,6,4这个无序序列进行简单选择排序，
 *
 * 首先要选择5以外的最小数来和5交换，也就是选择3和5交换，
 *
 * 一次排序后就变成了3,5,8,6,4.对剩下的序列一次进行选择和交换，
 *
 * 最终就会得到一个有序序列。其实选择排序可以看成冒泡排序的优化，
 *
 * 因为其目的相同，只是选择排序只有在确定了最小数的前提下才进行交换，
 *
 * 大大减少了交换的次数。选择排序的时间复杂度为O(n^2)
 */

public class 选择排序  extends SortBase{

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

}
