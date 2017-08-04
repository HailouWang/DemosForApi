package com.example.array;

/**
 * Created by ifei on 2017/8/1.
 *
 *
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 解题思路：
 * 选取右上角/左下角 的数字，如果比需要查找的整数要大，则该列/行，可以排除。
 * 如果比需要查找的整数小，则该行/列，可以排除。
 *
 * 直至查找完毕或者找到目标整数。
 *
 * 解题步骤：
 * 1、二维数组不为null，否则返回-1.
 * 2、二维码不符合约定条件，不合法，返回 -2.
 * 3、存在需要查找的整数，返回 1.
 * 4、不存在需要查找的整数，返回0.
 *
 */

public class 二维数组中的查找问题 {

    public static void main(String args[]){
        int[][] arrays = new int[][]{
                {1,3,6,9},
                {2,4,6,7},
                {5,7,9,10}
        };

        int findNumber1 = find(arrays,6);
        System.out.println("存在数字6："+(findNumber1 == 1));

        int findNumber2 = find(arrays,12);
        System.out.println("存在数字12："+(findNumber2 == 1));
    }

    public static int find(int[][] arrays,int number){
        if(arrays == null){
            return -1;
        }

        int rows = arrays.length;
        int columns = arrays[0].length;

        if(rows>0&&columns>0){
            //有效数组区域
            int row = 0,column = arrays[0].length - 1;
            while(row<rows&&column>=0){
                if(arrays[row][column] == number){
                    return 1;
                }
                if(arrays[row][column]>number){
                    --column;
                }else{
                    ++row;
                }
            }
        }
        return 0;
    }

    public static boolean isValidArray(int[][] arrays){
        if(arrays == null){
            return false;
        }

        int temp = 0;
        for(int i=0;i<arrays.length;i++){
            temp = arrays[i][0];
            for(int j=1;j<arrays[0].length;j++){
                if(arrays[i][j]<temp){
                    return false;
                }
                temp = arrays[i][j];
            }
        }
        return true;
    }
}
