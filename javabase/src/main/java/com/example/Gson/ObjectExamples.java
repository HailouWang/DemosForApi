package com.example.Gson;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ifei on 2017/9/8.
 *
 * 官方建议：
 * 1、使用private私有域
 * 2、不需要使用任何注释指示字段包含序列化和反序列化。当前类中的所有字段(和所有的超类)都包含在默认情况下。
 * 3、如果一个字段标记为瞬态(transient),(默认情况下)被忽略,而不是包含在JSON序列化或反序列化。
 * 4、这个实现正确处理nulls，空类型可以被正确处理
 *  - 序列化时,跳过一个空字段的输出
 *  -反序列化时,在JSON结果对象中失踪的条目设置相应的字段为null
 * 5、如果一个字段是合成的,它将被忽略,而不是包含在JSON序列化或反序列化
 * 6、字段对应于外部类的内部类,匿名类,和当地的类被忽略,而不是包含在序列化或反序列化
 *
 */

public class ObjectExamples {

    static class BagOfPrimitives {
        @SerializedName(value = "value1",alternate = {"myValue1","goodValue1"})
        private int value1 = 1;
        @SerializedName(value = "value2",alternate = "myValue2")
        private String value2 = "abc";
        private transient int value3 = 3;
        BagOfPrimitives() {
            // no-args constructor
        }

        @Override
        public String toString() {
            return "BagOfPrimitives{" +
                    "value1=" + value1 +
                    ", value2='" + value2 + '\'' +
                    ", value3=" + value3 +
                    '}';
        }
    }

    static class SubBagOfPrimitives extends BagOfPrimitives{}

    public static void main(String args[]){
        // Serialization
        //序列化
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        String json = gson.toJson(obj);

        System.out.println(ObjectExamples.class.getName()+","+json);

        // ==> json is {"value1":1,"value2":"abc"}

        // Deserialization
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        // ==> obj2 is just like obj
        System.out.println(ObjectExamples.class.getName()+","+(obj2 instanceof BagOfPrimitives));

        BagOfPrimitives obj3 = gson.fromJson("{\"value1\":1,\"value2\":\"abc\"}", BagOfPrimitives.class);
        // ==> obj2 is just like obj
        System.out.println(ObjectExamples.class.getName()+","+(obj2 instanceof BagOfPrimitives));

        System.out.println("------------------------->");
        SubBagOfPrimitives obj4 = gson.fromJson("{\"Value1\":6,\"myValue1\":7,\"goodValue1\":8,\"myValue2\":\"deg\"}", SubBagOfPrimitives.class);
        // ==> obj2 is just like obj
        System.out.println(ObjectExamples.class.getName()+","+(obj4));

    }

}
