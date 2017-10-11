package rxjava2.demo;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by ifei on 2017/9/18.
 */

public class Demos14_Flowable_range {

    public static void main(String args[]){
        Flowable.range(2,10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        System.out.println("------filter:integer:"+integer);
                        return true;
                    }
                }).take(8)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("-----i:"+integer);
                    }
                });
    }

}
