package rxjava2.demo;

import java.io.Serializable;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by ifei on 2017/9/18.
 */

public class Demos11_Flowable_fromxxx {

    public static void main(String args[]){

        //1、fromArray
        Flowable.fromArray(1,2,3,4,5,6,7)
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object object) throws Exception {
                        System.out.println("Flowable fromArray:----->"+object);
                    }
                });

        Integer[] commandi = new Integer[]{9,8,7};
        Flowable.fromArray(commandi)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer ints) throws Exception {
                        System.out.println("Flowable from Integer Array:----->"+ints);
                    }
                });

        System.out.println("2===================>");

        //2、
        Flowable.just("字符串",3)
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    public void accept(Serializable serializable) throws Exception {
                        System.out.println("Flowable just:----->"+serializable);
                    }
                });

        System.out.println("3===================>");

        //3、
        String[] commands = new String[]{"good","batter","best"};
        Flowable.fromArray(commands)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("Flowable fromString Array:----->"+s);
                    }
                });

        System.out.println("3===================>");


    }

}
