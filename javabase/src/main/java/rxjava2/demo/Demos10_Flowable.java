package rxjava2.demo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ifei on 2017/9/18.
 * Flowable的几种背压策略：
 1. BackpressureStrategy.ERROR：缓存区默人大小128，流速不均衡时发射MissingBackpressureException信号。
 2. BackpressureStrategy.BUFFER：缓存区不限制大小，使用不当仍会OOM。
 3. BackpressureStrategy.DROP：缓存最近的nNext事件。
 4. BackpressureStrategy.LATEST：缓存区会保留最后的OnNext事件，覆盖之前缓存的OnNext事件。
 5. BackpressureStrategy.MISSING：OnNext事件没有任何缓存和丢弃，下游要处理任何溢出。
 */

public class Demos10_Flowable {

    static Subscription mySubscription;

    public static void main(String args[]){
        //1、Flowable--->  Demos02_Observable_Observer

        //2、初始化Flowable

        /*
        缓冲区大小为128
        The default buffer size.
        static final int BUFFER_SIZE;
        static {
            BUFFER_SIZE = Math.max(1, Integer.getInteger("rx2.buffer-size", 128));
        }
         */

        Flowable<String> myFlowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("Flowable 11111");
                e.onNext("Flowable 22222");
                e.onNext("Flowable 33333");
                e.onNext("Flowable 44444");
                e.onNext("Flowable 55555");
                e.onNext("Flowable 66666");
                e.onNext("Flowable 77777");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io());

        Subscriber mySubscriber = new Subscriber() {

            @Override
            public void onError(Throwable e) {
                System.out.println("myObserver2 onError ------>"+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("myObserver2 onCompleted ------>");
            }

            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("myObserver2 onSubscribe ------>"+s);
                mySubscription = s;
            }

            @Override
            public void onNext(Object o) {
                System.out.println("myObserver2 onNext ------>"+o);
            }
        };

        myFlowable.subscribe(mySubscriber);

        mySubscription.request(3);
        mySubscription.request(2);
        mySubscription.request(4);
    }

}
