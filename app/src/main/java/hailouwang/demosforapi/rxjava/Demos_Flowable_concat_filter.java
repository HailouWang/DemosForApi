package hailouwang.demosforapi.rxjava;

import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import hailouwang.demosforapi.R;
import hailouwang.demosforapi.Utils;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.Flowable.concat;

public class Demos_Flowable_concat_filter extends Demos_Flowable_BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demos__flowable_complex);

        //案例一:过滤掉空
        Flowable flowable1 = Flowable.concat(flowableFromList1,floawableFromList2,floawableFromNull)
                .observeOn(Schedulers.io())
                .filter(new Predicate<List<AppInfo>>() {
                    @Override
                    public boolean test(@NonNull List<AppInfo> appInfos) throws Exception {
                        return appInfos!=null&&!appInfos.isEmpty();
                    }
                });

        flowable1.subscribe(new Consumer<List<AppInfo>>() {
            @Override
            public void accept(List<AppInfo> appInfos) throws Exception {
                Log.d(Utils.HLWANG_TAG,"==========================================>>>>");
                for(AppInfo appInfo:appInfos){
                    Log.d(Utils.HLWANG_TAG,"FlowableComplex:"+appInfo);
                }
            }
        });


    }
}
