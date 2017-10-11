package rxjava2.demo;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by ifei on 2017/9/18.
 */

public class Demos12_Flowable_concat {

    public static void main(String args[]){
        Flowable.concat(new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onNext("Publish1 1111");
                s.onNext("Publish1 222");
                s.onNext("Publish1 3333");
                s.onNext("Publish1 4444");
            }
        }, new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onNext("Publish2 1111");
                s.onNext("Publish2 222");
                s.onNext("Publish2 3333");
                s.onNext("Publish2 4444");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("----->"+s);
            }
        });
    }

}
