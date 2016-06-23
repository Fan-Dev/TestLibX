package com.crrain.testlibx;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

import static junit.framework.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentationTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.crrain.testlibx", appContext.getPackageName());

    }

    @Test
    public void testRXJava(){
        Observable.just("1").subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("asdsad");
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
}