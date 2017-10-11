package com.example.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;

/**
 * Created by ifei on 2017/9/9.
 */

public class VersioningSupportDemos {
    static class VersionedClass {
        @Since(1.1) private final String newerField;
        @Since(1.0) private final String newField;
        private final String field;

        public VersionedClass() {
            this.newerField = "newer";
            this.newField = "new";
            this.field = "old";
        }
    }

    public static void main(String args[]){
        VersionedClass versionedObject = new VersionedClass();
        Gson gson = new GsonBuilder().setVersion(1.0).create();

        String jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput);
        System.out.println();

        gson = new Gson();
        jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput);
    }
}
