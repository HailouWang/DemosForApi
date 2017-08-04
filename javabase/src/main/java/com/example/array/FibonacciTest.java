package com.example.array;

/**
 * Created by ifei on 2017/7/31.
 * 斐波那契数列（Fibonacci sequence），又称黄金分割数列，
 * 因数学家列昂纳多·斐波那契（Leonardoda Fibonacci）以兔子繁殖为例子而引入，
 * 故又称为“兔子数列”，指的是这样一个数列：1、1、2、3、5、8、13、21、34、……
 * 在数学上，斐波纳契数列以如下被以递归的方法定义：F(0)=0，F(1)=1, F(n)=F(n-1)+F(n-2)（n>=2，n∈N*）
 */

public class FibonacciTest {
    public static void main(String args[]) {
        int fib1 = Fib(0);
        int fib2 = Fib(1);
        int fib3 = Fib(2);
        int fib4 = Fib(3);
        int fib5 = Fib(4);
        int fib6 = Fib(5);
        int fib7 = Fib(6);

        System.out.println("0:" + fib1);
        System.out.println("1:" + fib2);
        System.out.println("2:" + fib3);
        System.out.println("3:" + fib4);
        System.out.println("4:" + fib5);
        System.out.println("5:" + fib6);
        System.out.println("6:" + fib7);

        System.out.println("-------------------------------");

        int fib11 = FibNormal(10);
        int fib12 = FibNormal(11);
        int fib13 = FibNormal(12);
        int fib14 = FibNormal(13);
        int fib15 = FibNormal(14);
        int fib16 = FibNormal(15);
        int fib17 = FibNormal(16);

        System.out.println("10:" + fib11);
        System.out.println("11:" + fib12);
        System.out.println("12:" + fib13);
        System.out.println("13:" + fib14);
        System.out.println("14:" + fib15);
        System.out.println("15:" + fib16);
        System.out.println("16:" + fib17);
    }

    /**
     * 递归
     *
     * @param n
     * @return
     */
    public static int Fib(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }
        return Fib(n - 1) + Fib(n - 2);
    }

    /**
     * 递推
     * @param n
     * @return
     */
    public static int FibNormal(int n){
        if (n <= 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }
        int n1 = 1,n2 = 1,nn = 0;
        for(int i=0;i<n-2;i++){
            nn = n1+n2;
            n1 = n2;
            n2 = nn;
        }
        return nn;
    }
}
