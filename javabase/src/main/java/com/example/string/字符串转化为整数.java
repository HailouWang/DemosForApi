package com.example.string;

/**
 * Created by ifei on 2017/8/1.
 * 字符串转为化整数，需要解决如下问题：
 * 1、字符串为null
 * 2、字符串不合法
 * 3、转化为几进制整数
 * 4、字符串也有范围，转化中是否存在越界的问题
 */

public class 字符串转化为整数 {

    public static void main(String args[]){
        String number = "148930";
        int parseNumbe = parseInt(number,10);
        System.out.println("字符串转换："+parseNumbe);
    }

    public static int parseInt(String s, int radix)
            throws NumberFormatException {
        /*
         * WARNING: This method may be invoked early during VM initialization before IntegerCache is
         * initialized. Care must be taken to not use the valueOf method.
         */
        // 一、异常处理、边界处理。
        // 1.1、字符串是否为null，如果是，抛出异常。
        if (s == null) {
            throw new NumberFormatException("null");
        }
        // 1.2、检查是否为，有效进制，如果非法，抛出异常。默认进制为10.
        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " greater than Character.MAX_RADIX");
        }

        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;//limit = -2147483647
        int multmin;
        int digit;
        // 二、解析字符串
        //1、从左到右开始解析字符串，将字符串中的解析的数字与result相加。
        //2、每循环一轮，result按照进制radix相乘一次，例如：result = 1，那么循环一次后，result = 10.
        //3、每循环一轮，result加上解析的数字，即：重复执行1、2步骤。
        if (len > 0) {
            char firstChar = s.charAt(0);
            // 2.1、有效字符串为[0~9a~f],如果小于0，则解析整数的正负 符号，其他符号，抛异常。
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+')
                    throw new NumberFormatException("s1----> " + s);

                if (len == 1) // Cannot have lone "+" or "-"
                    throw new NumberFormatException("s2----> " + s);
                i++;
            }
            multmin = limit / radix;//如果是10进制，radix = 10,则,multmin = -214748364
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(i++), radix);
                //2.2、如果整数位负，则抛出异常
                if (digit < 0) {
                    throw new NumberFormatException("s3----> " + s);
                }
                //2.3、multmin是在radix进制下的最小数，比这个还小，否则越界
                if (result < multmin) {
                    throw new NumberFormatException("s4----> " + s);
                }
                //2.4,每次循环都是result升级的过程，与进制相乘
                result *= radix;
                //2.5、limit是最小值，如果解析的digit与最小值相加，大于result，说明result将越界
                if (result < limit + digit) {
                    throw new NumberFormatException("s5----> " + s);
                }
                //2.6、此处将正数转化为负数来计算，故：设定为相减。也可以相加，上面需要同步调整。
                result -= digit;
            }
        } else {
            throw new NumberFormatException("s6----> " + s);
        }
        return negative ? result : -result;
    }
}
