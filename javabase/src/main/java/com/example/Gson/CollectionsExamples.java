package com.example.Gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ifei on 2017/9/8.
 *
 * 可以序列化任意对象的集合,但不能反序列化
 * -因为没有为用户显式生成的对象的类型
 * -反序列化时,集合必须是一个特定的泛型类型
 */

public class CollectionsExamples {
    public static void main(String args[]){
        Gson gson = new Gson();
        Collection<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);

        // Serialization 序列化
        String json = gson.toJson(ints);  // ==> json is [1,2,3,4,5]
        System.out.println(CollectionsExamples.class.getName()+",json:"+json);

        // Deserialization 反序列化
        Type collectionType = new TypeToken<Collection<Integer>>(){}.getType();
        Collection<Integer> ints2 = gson.fromJson(json, collectionType);
        // ==> ints2 is same as ints
        System.out.println(CollectionsExamples.class.getName()+",ints2:"+ints2);
    }
}
