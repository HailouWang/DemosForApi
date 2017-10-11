package rxjava2.demo;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by ifei on 2017/9/16.
 *
 * http://gank.io/post/560e15be2dca930e00da1083
 *
 */

public class Demos01_HelloWorld {

    public static void main(String args[]){
        Flowable.just("HelloWorld")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("--->"+s);
                    }
                });
    }

}
