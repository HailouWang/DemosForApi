package com.example.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ifei on 2017/9/22.
 */

public class GsonMaps {

    static class ToJsonBean{
        String name;
        int index,age;
        public ToJsonBean(int index,String name,int age){
            this.index = index;
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String args[]){
        Gson gson2 = new GsonBuilder().enableComplexMapKeySerialization().create();
        Map<String, ToJsonBean> map = new LinkedHashMap<String, ToJsonBean>();
        map.put("key1", new ToJsonBean(1, "小熊1", 21));
        map.put("key2", new ToJsonBean(2, "小熊2", 21));
        map.put("key3", new ToJsonBean(3, "小熊3", 21));
        map.put("key4", null);

        String json = gson2.toJson(map);

        System.out.println("maps---->String:"+json);


        Map<String, ToJsonBean> map1 = new LinkedHashMap<>();
        Type type = new TypeToken<Map<String, ToJsonBean>>() {}.getType();
        map1 = gson2.fromJson(json,type);
        System.out.println("json---->maps:"+map1
                +"，map1 linkedHashMap:"+(map1 instanceof LinkedHashMap)+"\n"
                +"，map1 LinkedTreeMap:"+(map1 instanceof LinkedTreeMap)+"\n");
    }
}
