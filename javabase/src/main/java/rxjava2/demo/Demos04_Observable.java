package rxjava2.demo;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by ifei on 2017/9/16.
 */

public class Demos04_Observable {

    public static void main(String args[]){
        //2、Observable create subscribe
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("do Thread Work!!!");
                e.onNext("sending data.....");
                e.onNext("do Local Work!!!");
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe ----->");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext ----->"+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError ----->");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete ----->");
            }
        });
    }
}
