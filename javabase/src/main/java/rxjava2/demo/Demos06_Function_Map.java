package rxjava2.demo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.subscribers.StrictSubscriber;

/**
 * Created by ifei on 2017/9/16.
 */

public class Demos06_Function_Map {

    static class User{
        String name;

        public User(String n){
            name = n;
        }
    }

    public static void main(String args[]){
        //1、map：一对一变换
        Observable.just("root")
                .map(new Function<String, User>() {

                    @Override
                    public User apply(@NonNull String s) throws Exception {
                        System.out.println("--------->"+s);
                        User user = new User(s);
                        return user;
                    }
                })
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        System.out.println("------User:"+user.name);
                    }
                });

        System.out.println("===================================================");

    }
}
