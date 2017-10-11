package com.example.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by ifei on 2017/9/9.
 * 默认行为是在Gson中的实现是忽略null对象字段的。这允许一个更紧凑的输出格式;
 * 然而,客户端必须为这些字段定义一个默认值为了从JSON格式转换回Java。
 */

public class NullsDemos {
    static class Foo {
        private final String s;
        private final int i;

        public Foo() {
            this(null, 5);
        }

        public Foo(String s, int i) {
            this.s = s;
            this.i = i;
        }
    }

    public static void main(String args[]){
        Gson gson = new GsonBuilder().serializeNulls().create();
        Foo foo = new Foo();
        String json = gson.toJson(foo);
        System.out.println(json);

        json = gson.toJson(null);
        System.out.println(json);
    }
}
