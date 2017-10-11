package com.example.Gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by ifei on 2017/9/8.
 *
 * 在处理泛型时，Gson没办法区分Foo还是Foo<Bar>，也就是泛型类型擦除。
 *
 * 需要使用：Type fooType = new TypeToken<Foo<Bar>>() {}.getType();
 * gson.toJson(foo, fooType);
 * gson.fromJson(json, fooType);
 */

public class GenericTypesDemos {
    static class Foo<T> {
        T value;
    }

    static class Bar{
        private String name="good";
    }

    public static void main(String args[]){
        Gson gson = new Gson();
        Foo<Bar> foo = new Foo<Bar>();
        foo.value = new Bar();

        String json = gson.toJson(foo); // 可能不会序列化成功，May not serialize foo.value correctly

        Foo<Bar> barFoo = gson.fromJson(json, foo.getClass()); // 反序列化失败 Fails to deserialize foo.value as Bar

        System.out.println(GenericTypesDemos.class.getName()+"1111,json:"+json
            +",barFoo:"+barFoo);
        //--------------------

        Type fooType = new TypeToken<Foo<Bar>>() {}.getType();
        String jsonWithType = gson.toJson(foo,fooType);

        Foo<Bar> barFooWithType = gson.fromJson(jsonWithType,fooType);
        System.out.println(GenericTypesDemos.class.getName()+"2222,jsonWithType:"+jsonWithType
            +",barFooWithType:"+barFooWithType);

    }

}
