package rxjava2.demo;

import java.util.ArrayList;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by ifei on 2017/9/18.
 */

public class Demos15_Flowable_complex {
    static class AppInfo{
        String name;
        String url;
        public AppInfo(String n,String u){
            name = n;
            url = u;
        }
    }

    static List<AppInfo> myAppList1 = new ArrayList<AppInfo>();
    static List<AppInfo> myAppList2 = new ArrayList<AppInfo>();

    static {
        myAppList1.add(new AppInfo("乐视视频","www.le.com"));
        myAppList1.add(new AppInfo("联想乐疯跑","www.lenovo.com"));
        myAppList1.add(new AppInfo("君正手表","www.ingenic.com"));

        myAppList2.add(new AppInfo("百度","www.baidu.com"));
        myAppList2.add(new AppInfo("小米","www.xiaomi.com"));
        myAppList2.add(new AppInfo("锤子","www.smartisan.com"));
        myAppList2.add(new AppInfo("美图手机","www.meitu.com"));
    }

    public static void main(String args[]){
        Flowable<List<AppInfo>> flowableFromList1 = Flowable.create(new FlowableOnSubscribe<List<AppInfo>>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<List<AppInfo>> e) throws Exception {
                e.onNext(myAppList1);
                e.onComplete();
            }
        }, BackpressureStrategy.MISSING);

        Flowable<List<AppInfo>> floawableFromList2 = Flowable.fromPublisher(new Publisher<List<AppInfo>>() {
            @Override
            public void subscribe(Subscriber<? super List<AppInfo>> s) {
                s.onNext(myAppList2);
                s.onComplete();
            }
        });

    }


}
