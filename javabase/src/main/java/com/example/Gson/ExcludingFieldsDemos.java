package com.example.Gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Modifier;

/**
 * Created by ifei on 2017/9/9.
 *
 * 默认情况下，transient和static修饰的变量会被Gson忽略。
 *
 * 可通过excludeFieldsWithModifiers加入
 *
 *
 * @Expose:
 * @Expose(deserialize = true,serialize = true)
 * 使用方法： 简单说来就是需要导出的字段上加上@Expose 注解，不导出的字段不加。注意是不导出的不加。
 */

public class ExcludingFieldsDemos {

    static class ExcludeFieldTest{
        transient String name ;
        static String age;
        String content;
        @Expose String other = "Other Info";

        public ExcludeFieldTest(){}

        public ExcludeFieldTest(String name,String age,String content){
            this.name = name;
            this.age = age;
            this.content = content;
        }

        @Override
        public String toString() {
            return "ExcludeFieldTest{" +
                    "name='" + name + '\'' +
                    ",age='" + age + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    public static void main(String args[]){
        Gson gson = new Gson();

        ExcludeFieldTest test = new ExcludeFieldTest("helloworld","18","编程");

        String json = gson.toJson(test,ExcludeFieldTest.class);
        System.out.println(ExcludeFieldTest.class.getName()+",json:"+json);

        Gson gsonWithExclude = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .create();

        String jsonWithTransient = gsonWithExclude.toJson(test,ExcludeFieldTest.class);
        System.out.println(ExcludeFieldTest.class.getName()+",jsonWithTransient:"+jsonWithTransient);


        Gson gsonWithExcludeTAG = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        String jsonWithTAG = gsonWithExcludeTAG.toJson(test,ExcludeFieldTest.class);
        System.out.println(ExcludeFieldTest.class.getName()+",jsonWithTAG:"+jsonWithTAG);
    }
}
