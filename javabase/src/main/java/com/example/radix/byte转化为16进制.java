package com.example.radix;

/**
 * Created by ifei on 2017/8/3.
 */

public class byte转化为16进制 {

    private static char[] HEXCHAR = "0123456789abcdef".toCharArray();

    public static void main(String args[]){
        byte[] bytes = new byte[]{(byte) 127,(byte)-128,(byte)0 };

        String hexString = toHexString(bytes);

        System.out.println("byte转化为16进制："+hexString);
    }

    public static String toHexString(byte[] bytes){
        // assert bytes != null : "Illegal value";
        if(bytes == null)return null;

        StringBuilder sb = new StringBuilder();
        for(Byte b:bytes){
            sb.append(HEXCHAR[(b & 0xf0)>>>4]);
            sb.append(HEXCHAR[b & 0x0f]);
        }
        return sb.toString();
    }
}
