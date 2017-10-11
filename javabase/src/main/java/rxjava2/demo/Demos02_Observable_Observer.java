package rxjava2.demo;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

/**
 * Created by ifei on 2017/9/16.
 */

public class Demos02_Observable_Observer {

    public static void main(String args[]){
        //1、定义主题，利用Create方法
        Observable<String> myObservale1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                System.out.println("Observable subscribe ------>"+e);
                e.onNext(">>>>>>>>>>>>>>>>>>subscribe1 ---onNext");
                e.onNext(">>>>>>>>>>>>>>>>>>subscribe2 ---onNext");
                e.onError(new Exception("就是抛异常~"));
                e.onNext(">>>>>>>>>>>>>>>>>>subscribe3 ---onNext");
                e.onComplete();
            }
        });
        //定义主题，利用just方法
        Observable<String> myObservable2 = Observable.just("Hello","rxJava","World!");
        //定义主题，利用from方法
        String[] words = new String[]{"Say","RxJava","Hello!"};
        Observable<String> myObservable3 = Observable.fromArray(words);

        Flowable<String> myFlowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("Flowable 11111");
                e.onNext("Flowable 22222");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR);

        //2、定义观察者
        Observer<String> myObserver1 = new Observer<String>(){

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Observer1 onSubscribe ------>"+d);
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("Observer1 onNext ------>"+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Observer1 onError ------>"+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Observer1 onComplete ------>");
            }
        };

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
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Object o) {
                System.out.println("myObserver2 onNext ------>"+o);
            }
        };

        //3、实现注册
        myObservale1.subscribe(myObserver1);
        myObservable2.subscribe(myObserver1);
        myObservable3.subscribe(myObserver1);

        myFlowable.subscribe(mySubscriber);

    }
    
}
