package com.example.radix;

/**
 * Created by ifei on 2017/8/3.
 */

public class Byte中进制转换 {

    /**
     * 1、使用Integer.parseInt得到整型
     * 2、检查是否越界
     * 3、得到byte对象
     * @param s
     * @param radix
     * @return
     * @throws Exception
     */
    private static byte parseByte(String s,int radix) throws Exception{
        int i = Integer.parseInt(s,radix);

        if(i<Byte.MIN_VALUE || i> Byte.MAX_VALUE){
            throw new Exception("");
        }

        return (byte) i;
    }

    /**
     * 缓存Byte 对象
     */
    private static class ByteCache{

        private static Byte[] byteCache = new Byte[-(-128)+127+1];

        public ByteCache(){
        }
        static{
            for(int i=0;i<byteCache.length;i++){
                byteCache[i] = new Byte((byte)(i-128));
            }
        }

    }
}
