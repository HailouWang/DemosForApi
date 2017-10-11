package com.example.Gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ifei on 2017/9/9.
 */

public class UserDefinedExclusionStrategies {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    public @interface Foo {
        // Field tag only annotation
    }

    static class SampleObjectForTest {
        @Foo private final int annotatedField;
        private final String stringField;
        private final long longField;

        public SampleObjectForTest() {
            annotatedField = 5;
            stringField = "someDefaultValue";
            longField = 1234;
        }
    }

    static class MyExclusionStrategy implements ExclusionStrategy {

        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            System.out.println(MyExclusionStrategy.class.getName()+",shouldSkipField :"+f);
            if(f.getAnnotation(Foo.class)!=null){
                return true;
            }
            return false;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            System.out.println(MyExclusionStrategy.class.getName()+",shouldSkipClass clazz:"+clazz);
            return false;
        }
    }

    public static void main(String args[]){
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new MyExclusionStrategy())
                .create();

        SampleObjectForTest test = new SampleObjectForTest();
        String sampleObjectJson = gson.toJson(test);
        System.out.println(UserDefinedExclusionStrategies.class.getName()+",main:"+sampleObjectJson);
    }
}
