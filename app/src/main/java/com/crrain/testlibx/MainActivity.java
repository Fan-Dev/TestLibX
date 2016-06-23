package com.crrain.testlibx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.just(getValue()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d("1", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("1", "onError " + e);
            }

            @Override
            public void onNext(String s) {
                Log.d("1", "onNext " + s);
            }
        });
    }

    private String getValue() {
        return "asdsa";
    }
}
