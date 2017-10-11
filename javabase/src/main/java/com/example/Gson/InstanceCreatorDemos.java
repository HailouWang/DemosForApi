package com.example.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ifei on 2017/9/9.
 */

public class InstanceCreatorDemos {

    static class Monkey{
        double number;
        String type;

        public Monkey(){}

        public Monkey(double n,String t){
            number = n;
            type = t;
        }

        @Override
        public String toString() {
            return "Monkey{" +
                    "number=" + number +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    static class MoneyInstanceCreator implements InstanceCreator<Monkey> {

        @Override
        public Monkey createInstance(Type type) {
            System.out.println(MoneyInstanceCreator.class.getName()+"...createInstance");
            return new Monkey(1000, "US");
        }
    }

    public static void main(String args[]){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Monkey.class,new MoneyInstanceCreator())
                .create();

        //无参
        Monkey monkey = new Monkey(2000,"China");

        String json = gson.toJson(monkey,Monkey.class);
        System.out.println(InstanceCreatorDemos.class.getName()+",json:"+json);

        Monkey monkey1 = gson.fromJson(json,Monkey.class);

        System.out.println(InstanceCreatorDemos.class.getName()
            +",monkey1:"+monkey1);

        //有参

        Id id = new Id(String.class.getComponentType(),600);
        Gson gsonWithIdInstanceCreator = new GsonBuilder()
                .registerTypeAdapter(Id.class,new IdInstanceCreator())
                .create();

        Type type = new TypeToken<Id<String>>() {}.getType();
        String idToJson = gsonWithIdInstanceCreator.toJson(id,type);
        System.out.println(InstanceCreatorDemos.class.getName()+",idToJson:"+idToJson);

        Id<String> idFromJson = gsonWithIdInstanceCreator.fromJson(idToJson,type);
        System.out.println(InstanceCreatorDemos.class.getName()+",idFromJson:"+idFromJson);

    }

    static class Id<T> {
        private final Class<T> classOfId;
        private final long value;
        public Id(Class<T> classOfId, long value) {
            this.classOfId = classOfId;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Id{" +
                    "classOfId=" + classOfId +
                    ", value=" + value +
                    '}';
        }
    }

    static class IdInstanceCreator implements InstanceCreator<Id<?>> {
        public Id<?> createInstance(Type type) {
            System.out.println("IdInstanceCreator createInstance--------------------------");
            Type[] typeParameters = ((ParameterizedType)type).getActualTypeArguments();
            Type idType = typeParameters[0]; // Id has only one parameterized type T
            return new Id((Class)idType, 0L);
        }
    }

}
