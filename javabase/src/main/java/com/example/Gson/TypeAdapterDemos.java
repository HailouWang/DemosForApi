package com.example.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ifei on 2017/9/9.
 */

public class TypeAdapterDemos {

    static class MyTypeAdapter extends TypeAdapter{

        @Override
        public void write(JsonWriter out, Object value) throws IOException {
            System.out.println(MyTypeAdapter.class.getName()+",write:"+value);
            out.beginObject();
            if(value instanceof ArbitraryTypesCollection.Event){
                ArbitraryTypesCollection.Event event = (ArbitraryTypesCollection.Event) value;
                out.name("event_name").value(event.name);
                out.name("event_type").value(event.type);
                out.name("event_date").value(event.date.toString());
            }
            out.endObject();
        }

        @Override
        public Object read(JsonReader in) throws IOException {
            System.out.println(MyTypeAdapter.class.getName()+",read:--------------------");
            ArbitraryTypesCollection.Event event = new ArbitraryTypesCollection.Event();
            in.beginObject();
            while(in.hasNext()){
                switch(in.nextName()){
                    case "event_name":
                        event.name = in.nextString();
                        break;
                    case "event_type":
                        event.type = in.nextString();
                        break;
                    case "event_date":
                        event.date = in.nextString();
                }
            }
            in.endObject();
            return event;
        }
    }

    public static void main(String args[]){
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add("数字1");
        collection.add(new ArbitraryTypesCollection.Event<Integer>("event","敲击",19));

        Gson gson = new Gson();
        String json = gson.toJson(collection);
        System.out.println(TypeAdapterDemos.class.getName()+",json:"+json);

        Gson gsonWithTypeAdapter = new GsonBuilder()
                .registerTypeAdapter(ArbitraryTypesCollection.Event.class,new MyTypeAdapter()).create();
        String toCollectionJson = gsonWithTypeAdapter.toJson(collection);
        System.out.println(TypeAdapterDemos.class.getName()+",toCollectionJson"+toCollectionJson);

        ArrayList arrayListFromJson = gsonWithTypeAdapter.fromJson(json, ArrayList.class);
        System.out.println(TypeAdapterDemos.class.getName()+",arrayListFromJson"+arrayListFromJson);
    }

}
