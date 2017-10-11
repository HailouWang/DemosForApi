package com.example.Gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ifei on 2017/9/9.
 *
 * 重命名：SerializedName
 *
 */

public class SerializedNameTag {
    static class SomeObject {
        @SerializedName("custom_naming") private final String someField;
        private final String someOtherField;

        public SomeObject(String a, String b) {
            this.someField = a;
            this.someOtherField = b;
        }
    }

    public static void main(String args[]){
        SomeObject someObject = new SomeObject("first", "second");
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        String jsonRepresentation = gson.toJson(someObject);
        System.out.println(jsonRepresentation);
    }

}
