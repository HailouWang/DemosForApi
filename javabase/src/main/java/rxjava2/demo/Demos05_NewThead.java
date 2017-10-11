package rxjava2.demo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.schedulers.Schedulers;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

/**
 * Created by ifei on 2017/9/16.
 */

public class Demos05_NewThead {
    public static void main(String args[]){
        System.out.println("Main Thread id:"+Thread.currentThread().getId());
        //线程变换
        Observable.just(1,2,3,4,5)
//                .subscribeOn(new NewThreadScheduler())//指定subscribe所在的线程
                .observeOn(new NewThreadScheduler())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        System.out.println("Map Thread Id:"+Thread.currentThread().getId());
                        return integer+10;
                    }
                })
                .observeOn(new NewThreadScheduler())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe Thread Id:"+Thread.currentThread().getId());
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("onNext Thread Id:"+Thread.currentThread().getId()+",number:"+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError Thread Id:"+Thread.currentThread().getId());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete Thread Id:"+Thread.currentThread().getId());
                    }
                });

    }
}
