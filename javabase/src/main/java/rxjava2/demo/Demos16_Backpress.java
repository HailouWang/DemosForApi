package rxjava2.demo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

/**
 * Created by ifei on 2017/9/18.
 */

public class Demos16_Backpress {

    public static void main(String args[]){

        Flowable.range(1,10000)
//                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("onSubscribe--->s:"+s);
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("--->"+o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError--->t:"+t);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete--->");
                    }
                });

    }

}
