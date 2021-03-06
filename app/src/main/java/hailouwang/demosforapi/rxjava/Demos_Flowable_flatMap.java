package hailouwang.demosforapi.rxjava;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import hailouwang.demosforapi.R;
import hailouwang.demosforapi.Utils;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static android.os.Build.VERSION_CODES.O;

public class Demos_Flowable_flatMap extends Demos_Flowable_BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demos__flowable_complex);

        //1、flatMap
        final Flowable flowable1 = Flowable.concat(flowableFromList1,floawableFromList2,floawableFromNull)
                .observeOn(Schedulers.io())
                .filter(new Predicate<List<AppInfo>>() {
                    @Override
                    public boolean test(@NonNull List<AppInfo> appInfos) throws Exception {
                        return appInfos!=null&&!appInfos.isEmpty();
                    }
                });

        Flowable flowableFromFlatMap = flowable1.flatMap(new Function<List<AppInfo>,Flowable<AppInfo>>(){

            @Override
            public Flowable<AppInfo> apply(@NonNull List<AppInfo> appInfos) throws Exception {
//                Log.d(Utils.HLWANG_TAG,"FlowableComplex flatMap o:"+appInfos);
                return Flowable.fromIterable(appInfos);
            }
        });
        flowableFromFlatMap.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(Utils.HLWANG_TAG,"FlowableComplex accept o:"+o);
            }
        });


        System.out.println("######################################");

    }
}
