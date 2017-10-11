package rxjava2.demo;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by ifei on 2017/9/18.
 */

public class Demos13_Flowable_flatMap {

    public static void main(String args[]){

        //1、打印字符数组中的数字和字母分开打印
        String[] stringArray1 = new String[]{"1","a","2","b","3","c","4","d"};
        String[] stringArray2 = new String[]{"11","aa","22","bb","33","cc","44","dd"};

        List<String[]> list = new ArrayList<>();
        list.add(stringArray1);
        list.add(stringArray2);

        Flowable.fromIterable(list)
                .flatMap(new Function<String[], Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(@NonNull final String[] strings) throws Exception {
                        return new Publisher<String>() {
                            @Override
                            public void subscribe(Subscriber<? super String> s) {
                                for(int i=0;i<strings.length;i++){
                                    if(i%2==0){
                                        s.onNext(strings[i]);
                                    }
                                }
                                for(int i=0;i<strings.length;i++){
                                    if(i%2!=0){
                                        s.onNext(strings[i]);
                                    }
                                }
                            }
                        };
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("---->"+s);
            }
        });
    }

}
