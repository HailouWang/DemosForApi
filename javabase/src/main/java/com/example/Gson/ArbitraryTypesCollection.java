package com.example.Gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by ifei on 2017/9/8.
 * 任意类型的集合
 * 方案1、使用
 */

public class ArbitraryTypesCollection {

    static class Event<D>{
        String name;
        String type;
        D date;

        public Event(){}

        public Event(String n,String t,D d){
            name = n;
            type = t;
            date = d;
        }

//        @Override
//        public String toString() {
//            return String.format("name=%s,type=%s",name,type);
//        }
    }

    public static void main(String args[]){
        //方案1 使用JsonParser来解析
        Gson gson = new Gson();

        Collection collection = new ArrayList();
        collection.add(1);
        collection.add("数字1");
        collection.add(new Event<Integer>("event","敲击",19));

        String json = gson.toJson(collection);
        System.out.println(ArrayExamples.class.getName()+",json:"+json);

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();
        int number = gson.fromJson(jsonArray.get(0),Integer.class);
        String value = gson.fromJson(jsonArray.get(1),String.class);
        Event event = gson.fromJson(jsonArray.get(2),Event.class);

        System.out.println(ArrayExamples.class.getName()+",number:"+number
                +",value:"+value
                +",event:"+event);

    }
}
