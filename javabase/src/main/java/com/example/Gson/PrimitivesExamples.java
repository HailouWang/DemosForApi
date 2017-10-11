package com.example.Gson;

import com.google.gson.Gson;

/**
 * Created by ifei on 2017/9/8.
 */

public class PrimitivesExamples {

    public static void main(String args[]){
        // Serialization 序列化
        Gson gson = new Gson();
        String toGson1 = gson.toJson(1);            // ==> 1
        String toGsonChar = gson.toJson("abcd");       // ==> "abcd"
        String toGsonLong = gson.toJson(new Long(10)); // ==> 10
        String toGsonDouble = gson.toJson(new Double(10.99)); // ==> 10.99
        int[] values = { 1,2,3 };
        String toGsonArray = gson.toJson(values);       // ==> [1]

        System.out.println("Gson char:"+toGsonChar
                +",Gson number:"+toGson1
                +",Gson long:"+toGsonLong
                +",Gson double:"+toGsonDouble
                +",Gson toGsonArray:"+toGsonArray);


        // Deserialization 反序列化
        int one = gson.fromJson("1", int.class);
        Integer oneInInteger = gson.fromJson("1", Integer.class);
        Long oneInLong = gson.fromJson("1", Long.class);
        Double oneDouble = gson.fromJson("10.99",Double.class);
        Boolean gsonBoolean = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);

        System.out.println("Gson fromJson:"+one
                +",oneInInteger:"+oneInInteger
                +",oneInLong:"+oneInLong
                +",oneDouble:"+oneDouble
                +",gsonBoolean:"+gsonBoolean
                +",str:"+str
                +",anotherStr:"+toStringFromArray(anotherStr));
    }

    static String toStringFromArray(String[] array){
        if(array == null)return "";
        StringBuilder sb = new StringBuilder();
        for(String str:array){
            sb.append(str);
        }
        return sb.toString();
    }
}
