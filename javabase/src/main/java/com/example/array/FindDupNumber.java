package com.example.array;

import java.util.HashSet;

/**
 * Created by ifei on 2017/7/31.
 * 面试题三
 */

public class FindDupNumber {

    public static void main(String args[]){
        int[] numbes = new int[]{2,3,1,0,2,5,3};
        int dupNumber = findDupNumber(numbes);
        System.out.println("FindDupNumber main dupNumber = "+dupNumber);

        int onlyDupNumber = findDupNumber2(new int[]{1,3,5,7,9,7,2,4,6,8});
        System.out.println("FindDupNumber2 only dup number = "+onlyDupNumber);

        int dumNumberByHash = findDupByHash(numbes);
        System.out.println("FindDupNumber dup Number :"+dumNumberByHash);

        int dupNumberByDivision = findDupNumberByDivision(new int[]{0,1,1,2,3});
        System.out.println("findDupNumberByDivision dup Number is:"+dupNumberByDivision);
    }

    /**
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字是重复的。
     * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者3
     *
     * 思路：
     * 从头到尾扫描数组每个数字，当扫描到下标为i的数字m时，
     * 首先比较m是不是等于i,如果是，继续扫描；
     * 如果不是，再拿m和第m个数字进行比较。
     * 如果他们相等，就找到第一个重复数字，如果不相等，交换两者位置。
     * 接下来重复上述过程，直到找到第一个重复数字。
     *
     * 步骤：
     * 1、如果数组为null，则返回-1.
     * 2、遍历数组，如果元素不在0~n-1之内，则返回-1，说明输入错误。
     * 3、嵌套循环。外环控制依次元素位。内环控制是否交换元素。
     * @param arrays
     * @return
     */
    public static int findDupNumber(int[] arrays){
        //1、如果数组为null，则返回-1.
        if(arrays == null )return -1;

        //2、遍历数组，如果元素不在0~n-1之内，则返回-1，说明输入错误。
        int length = arrays.length;
        for(int i=0;i<length;i++){
            if(arrays[i]<0||arrays[i]>=length){
                return -1;
            }
        }

        //3、嵌套循环
        int temp;
        for(int i=0;i<length;i++){
            //3.1、判断下标为i的数字m和i是否相等,如果是，继续扫描；
            while(arrays[i]!=i){//3.2、如果不是，再拿m和第m个数字进行比较。
                if(arrays[i] == arrays[arrays[i]]){//3.3、如果他们相等，就找到第一个重复数字，如果不相等，交换两者位置。
                    return arrays[i];
                }
                //3.4、交换。将array[i] = m，放在第m位。
                temp = arrays[i];
                arrays[i] = arrays[temp];
                arrays[temp] = temp;
            }
        }
        return -1;
    }

    /**
     * 思路：如果要求中，只有一个数字是重复的，则：可以使用  数组之和 - [0~n-1]的和，差值就是重复数字
     *
     * 解题思路：
     * 1、arrays是否为null，如果是，返回-1.
     * 2、数字是否分布在 0~n-1
     * @param arrays
     * @return
     */
    public static int findDupNumber2(int[] arrays){
        //1、如果arrays == null，返回 -1.
        if(arrays == null)return -1;

        //2、数组元素是否分布在0~n-1之内
        for(int i=0;i<arrays.length;i++){
            if(arrays[i]<0||arrays[i]>=arrays.length){
                return -1;
            }
        }

        //3、
        int sumCount = 0,sumDup = 0;
        for(int i=0;i<arrays.length;i++){
            sumDup += arrays[i];
            sumCount += i;
        }

        return sumDup - sumCount;
    }

    /**
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字是重复的。
     * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者3
     *
     * 解题思路：
     * 从头到尾按顺序扫描数组的每个数字，每扫到一个数字，
     * 如果Hash中，还没有这个数字，就把它加入Hash表。
     * 如果已经存在了该数字，就找到了一个重复汉字。
     *
     * 解题步骤：
     * 1、如果arrays 为null，则返回 -1；
     * 2、检查arrays中的数字是否分布在[0~n-1]中
     * 3、使用Hash 判别是否重复数字
     * @param arrays
     * @return
     */
    public static int findDupByHash(int[] arrays){
        if(arrays == null){
            return -1;
        }

        for(int i=0;i<arrays.length;i++){
            if(arrays[i]<0||arrays[i]>=arrays.length){
                return -1;
            }
        }

        HashSet hash = new HashSet();
        for(int i=0;i<arrays.length;i++){
            if(hash.contains(arrays[i])){
                return arrays[i];
            }
            hash.add(arrays[i]);
        }
        return -1;
    }

    /**
     * 长度为n的数组里的所有数字都在0~n-1里，数组中至少有一个数字是重复的。
     * 请找出数组中任意一个重复数字，但不能修改输入的数组。
     *
     * 例如：输入的长度为8的数组{2，3，5，4，3，2，6，7}，
     * 那么，对应的输出是重复数字 2 或 3。
     *
     * 思路：数字分布在0~n-1里，故：根据数组长度，
     * 从中间数字m将 0~n-1，截断，分为两部分。0~m-1，m~n-1。
     *
     * 如果0~m-1的数字在数组中的数目超过m，那么一定包含重复数字；
     * 继续以上步骤，直到找到一个重复的数字。
     *
     * 解题步骤：
     * 1、arrays数组不为null，否则返回 -1.
     * 2、数组数字分布在0~n-1中。
     * 3、截断，二分查找。
     *
     * @param arrays
     * @return
     * -1:null
     * -2:valid number in array
     * -3:un found
     */
    public static int findDupNumberByDivision(int[] arrays){
        if(arrays == null){
            return -1;
        }

        for(int i=0;i<arrays.length;i++){
            if(arrays[i]<0||arrays[i]>=arrays.length){
                return -2;
            }
        }

        //3、
        int start =0,end = arrays.length-1;
        while(end>=start){
            int middle = ((end - start)>>1)+start;

            int countRange = countRange(arrays,start,middle);
            if(end == start){
                if(countRange>1){
                    return start;
                }else{
                    break;
                }
            }

            if(countRange>(middle-start)+1){
                end = middle;
            }else{
                start = middle+1;
            }
        }
        return -3;
    }

    public static int countRange(int[] arrays,int start,int end){
        if(arrays == null){
            return 0;
        }

        int count=0;
        for(int i=0;i<arrays.length;i++){
            if(arrays[i]>=start&&arrays[i]<=end){
                count++;
            }
        }
        return count;
    }
}
