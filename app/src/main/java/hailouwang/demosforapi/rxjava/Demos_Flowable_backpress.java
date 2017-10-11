package hailouwang.demosforapi.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import hailouwang.demosforapi.R;
import hailouwang.demosforapi.Utils;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Demos_Flowable_backpress extends AppCompatActivity {
    Subscription mySubscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        myFlowable.onBackpressureDrop();

        Subscriber mySubscriber = new Subscriber() {

            @Override
            public void onError(Throwable e) {
                Log.d(Utils.HLWANG_TAG,"myObserver2 onError ------>"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(Utils.HLWANG_TAG,"myObserver2 onCompleted ------>");
            }

            @Override
            public void onSubscribe(Subscription s) {
                Log.d(Utils.HLWANG_TAG,"myObserver2 onSubscribe ------>"+s);
                mySubscription = s;
            }

            @Override
            public void onNext(Object o) {
                Log.d(Utils.HLWANG_TAG,"myObserver2 onNext ------>"+o);
            }
        };

        myFlowable.subscribe(mySubscriber);

        mySubscription.request(3);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mySubscription.request(2);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mySubscription.request(4);
    }
}
