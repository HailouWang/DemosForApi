package com.example.Gson;

import com.google.gson.Gson;

/**
 * Created by ifei on 2017/9/8.
 */

public class ArrayExamples {
    public static void main(String args[]){
        Gson gson = new Gson();
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};

        // Serialization
        // 序列化
        String intArray = gson.toJson(ints);     // ==> [1,2,3,4,5]
        String stringArray = gson.toJson(strings);  // ==> ["abc", "def", "ghi"]

        System.out.println(ArrayExamples.class.getName()+",intArray:"+intArray
                +",stringArray:"+stringArray);

        // Deserialization
        // 反序列化
        int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);
        // ==> ints2 will be same as ints
        System.out.println(ArrayExamples.class.getName()+",intArray:"+toStringFromArray(ints2));

    }

    static String toStringFromArray(int[] array){
        if(array == null)return "";
        StringBuilder sb = new StringBuilder();
        for(Object str:array){
            sb.append(str);
        }
        return sb.toString();
    }
}
