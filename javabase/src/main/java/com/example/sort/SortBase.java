package com.example.sort;

import java.util.ArrayList;

/**
 * Created by ifei on 2017/8/7.
 */

public class SortBase {

    public static void print(int[] array){
        if(array==null)return;
        for(int i=0;i<array.length;i++){
            System.out.print("    "+array[i]);
        }
        System.out.println();
    }

    public static void print(String message,int[] array){
        System.out.println(message+"----------------------");
        print(array);
    }

    public static void print(ArrayList array){
        if(array == null)return;
        for(Object object:array){
            System.out.print("    "+object);
        }
        System.out.println();
    }

    public static void print(String message,ArrayList array){
        System.out.println(message+"----------------------");
        print(array);
    }
}
