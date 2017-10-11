package com.example.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ifei on 2017/9/30.
 */

public class ArrayListTest {
    public static void main(String args[]){
       ArrayList<Object> list = new ArrayList<>();
        addItemIntoList(list,"11111");

        printList(list);
    }

    /**
     * 测试在方法中修改集合，是否生效 = 生效
     * @param list
     * @param obj
     */
    private static void addItemIntoList(List<Object> list, Object obj){
        if(list !=null){
            list.add(obj);
        }
    }

    private static void printList(List<Object> list){
        if(list!=null){
            for(Object obj:list){
                System.out.println("------>"+obj);
            }
        }
    }

}
