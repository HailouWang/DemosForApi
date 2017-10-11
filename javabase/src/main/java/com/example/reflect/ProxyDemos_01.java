package com.example.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * Created by ifei on 2017/9/15.
 * http://www.cnblogs.com/xiaoluo501395377/p/3383130.html
 * http://blog.csdn.net/rokii/article/details/4046098
 */

public class ProxyDemos_01 {

    protected static interface IRealObject1{
        public void doSomething1();
    }

    protected static interface IRealObject2{
        public void doSomething2();
    }

    protected static class RealObject implements IRealObject1,IRealObject2{

        public void doOtherThing(){
            System.out.println("-----RealObject...doOtherThing...");
        }

        @Override
        public void doSomething1() {
            System.out.println("-----RealObject...doSomething1111111...");
        }

        @Override
        public void doSomething2() {
            System.out.println("-----RealObject...doSomething2222222...");
        }
    }

    protected static class RealObjectProxy implements InvocationHandler{

        private RealObject realObject;

        public RealObjectProxy(RealObject mRealObject){
            realObject = mRealObject;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            System.out.println("RealObjectProxy before invoke....Method:"+method.getName());

            Object result = method.invoke(realObject,args);
            System.out.println("RealObjectProxy invoke....result:"+result);

            System.out.println("RealObjectProxy after invoke....");
            return result;
        }
    }

    public static void main(String args[]){
        RealObject realObject = new RealObject();
        RealObjectProxy proxy = new RealObjectProxy(realObject);

        System.out.println("Interfaces ----------start");
        Class[] realObjectInterfce = realObject.getClass().getInterfaces();
        for(Class clazz:realObjectInterfce){
            System.out.println("Interfaces clazz name:"+clazz.getName());
        }
        System.out.println("Interfaces ----------end");

        /**
         * Demo1
         * 1、newProxyInstance的第二个参数，必须为Interface,此处只传递一个interface
         */
        Object proxyResult1 = Proxy.newProxyInstance(RealObject.class.getClassLoader(),
                new Class[]{IRealObject1.class},proxy);

        System.out.println("Main Method.....proxyResult className:"+proxyResult1.getClass().getName() + "\n"
                +"{proxyResult1 == RealObject？"+(proxyResult1 instanceof RealObject) + "} \n "
                +"{proxyResult1 == IRealObject1？"+(proxyResult1 instanceof IRealObject1)+ "} \n "
                +"{proxyResult1 == IRealObject2？"+(proxyResult1 instanceof IRealObject2)+ "} \n "
        );

        ((IRealObject1)proxyResult1).doSomething1();

        System.out.println("===============================");

        /**
         * Demo2
         * 1、newProxyInstance的第二个参数，必须为Interface,此处只传递一个interface
         */
        Object proxyResult2 = (IRealObject2) Proxy.newProxyInstance(RealObject.class.getClassLoader(),
                realObject.getClass().getInterfaces(),proxy);

        System.out.println("Main Method.....proxyResult className:"+proxyResult2.getClass().getName() + "\n"
                +"{proxyResult2 == RealObject？"+(proxyResult2 instanceof RealObject) + "} \n "
                +"{proxyResult2 == IRealObject1？"+(proxyResult2 instanceof IRealObject1)+ ",clazz name :"+proxyResult2.getClass().getName()+"} \n "
                +"{proxyResult2 == IRealObject2？"+(proxyResult2 instanceof IRealObject2)+ ",clazz name :"+proxyResult2.getClass().getName()+"} \n "
        );

        ((IRealObject1)proxyResult2).doSomething1();
        ((IRealObject2)proxyResult2).doSomething2();

    }
}
