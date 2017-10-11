package com.example.reflect;

/**
 * Created by ifei on 2017/9/28.
 */

public class ClazzName {
    public static void main(String args[]){
        Object obj = getItem(Item1.class);
        System.out.println("obj:------>"+obj);

        String clazzName = Item1.class.getName();
        System.out.println("clazzName:------>"+clazzName);
        Object obj1;
        try {
            obj1 = getItem(Class.forName(clazzName));
            System.out.println("obj1:------>"+obj1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Item1{
        String type = "item1";

        @Override
        public String toString() {
            return "Item1 [type=" + type + "]";
        }

    }

    public static <T> T getItem(Class<T> t){
        Object obj = new Item1();
        Class clazz = obj.getClass();
        String name = t.getName();//test1.mytest.TMain$Item1
        String className = t.getSimpleName();//Item1
        String clazzName = t.getCanonicalName();//test1.mytest.TMain.Item1
        String typeName = t.getTypeName();//test1.mytest.TMain$Item1
        Object clazzObj = t.getClass();//class java.lang.Class
        System.out.println("--> name:"+name
        +",className:"+className
        +",clazzName:"+clazzName
        +",typeName:"+typeName
        +",clazzObj:"+clazzObj);
        if(obj.getClass().getCanonicalName().equals(t.getCanonicalName())){
            return (T) obj;
        }
        return null;
    }
}
