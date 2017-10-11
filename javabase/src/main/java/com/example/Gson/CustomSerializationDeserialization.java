package com.example.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Created by ifei on 2017/9/9.
 * 自定义序列化和反序列化
 */

public class CustomSerializationDeserialization {
    static class DateTime{
        private long time;
        public DateTime(long t){
            time = t;
        }

        @Override
        public String toString() {
            return new SimpleDateFormat("HH:mm:ss").format(time).toString();
        }
    }

    static class DateTimeSerializer implements JsonSerializer<DateTime> {
        @Override
        public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
            System.out.println("DateTimeSerializer...serialize toString:"+src.toString());
            return new JsonPrimitive(src.time);
        }
    }

    static class DateTimeDeserializer implements JsonDeserializer<DateTime> {

        @Override
        public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            System.out.println("DateTimeDeserializer...deserialize toString:"+json.getAsJsonPrimitive().getAsString());
            return new DateTime(json.getAsLong());
        }
    }

    public static void main(String args[]){
        Gson gson = new Gson();

        DateTime dateTime = new DateTime(System.currentTimeMillis());

        String dateTimeSerializer = gson.toJson(dateTime);
        System.out.println(CustomSerializationDeserialization.class.getName()
                +",FirstTime Serializer:"+dateTimeSerializer);

        //使用registerTypeAdapter进行获取,可以只选择注册其中的一个。
        gson = new GsonBuilder()
                .registerTypeAdapter(DateTime.class,new DateTimeSerializer())
                .registerTypeAdapter(DateTime.class,new DateTimeDeserializer()).create();
        dateTimeSerializer = gson.toJson(dateTime);
        System.out.println(CustomSerializationDeserialization.class.getName()
                +",SecondTime Serializer:"+dateTimeSerializer);

        DateTime dateTimeFromJson = gson.fromJson(dateTimeSerializer,DateTime.class);
        System.out.println(CustomSerializationDeserialization.class.getName()
                +",SecondTime deserialize:"+dateTimeFromJson);

    }

}
