package com.example.string;

/**
 * Created by ifei on 2017/8/1.
 */

public class 替换字符串中的空格为其他字符 {

    public static void main(String args[]){
        String temp = "123 456 789 ";
        temp = replaceSpaceChar(temp,"/**/");
        System.out.println("replace :"+temp);
    }

    /**
     * 1、字符串不为null，否则返回null
     * 2、如果字符为空，则使用"replaceMent"替换。
     * @param s
     * @return
     */
    public static String replaceSpaceChar(String s,String replaceMent){
        //1、如果s == null，则返回null。
        if(s == null){
            return s;
        }
        //2、如果字符为空，则使用/**/替换。
        char[] stringChar = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<stringChar.length;i++){
            char c = stringChar[i];
            if(c == ' '){
                sb.append(replaceMent);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
