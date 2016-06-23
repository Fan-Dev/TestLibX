package com.example;


import com.example.entity.IpInfo;
import com.example.response.BaseResponse;
import com.example.service.TaoBaoApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

public class MainTest {
    public static void main(String[] args) {
//        Observable.just(getValue()).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                syso("onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                syso("onError " + e);
//            }
//
//            @Override
//            public void onNext(String s) {
//                syso("onNext " + s);
//            }
//        });

        Subscriber subscriber01 = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                syso("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                syso("onError " + e);
            }

            @Override
            public void onNext(String s) {
                syso("onNext " + s);
            }
        };


        Subscriber subscriber02 = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                syso("onCompleted22");
            }

            @Override
            public void onError(Throwable e) {
                syso("onError22 " + e);
            }

            @Override
            public void onNext(String s) {
                syso("onNext222 " + s);
            }
        };


        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(getValue());
            }
        });

        observable.subscribe(subscriber01);
        observable.subscribe(subscriber02);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ip.taobao.com").addConverterFactory(GsonConverterFactory.create()).build();
        TaoBaoApiService taoBaoApiService = retrofit.create(TaoBaoApiService.class);

        Call<BaseResponse<IpInfo>> call = taoBaoApiService.getIpInfo("220.248.17.90");
        call.enqueue(new Callback<BaseResponse<IpInfo>>() {
            @Override
            public void onResponse(Call<BaseResponse<IpInfo>> call, Response<BaseResponse<IpInfo>> response) {

                response.headers();

                BaseResponse<IpInfo> ipInfoRespon = response.body();
                IpInfo ipInfo = ipInfoRespon.getData();
                syso(ipInfo.toString());
            }

            @Override
            public void onFailure(Call<BaseResponse<IpInfo>> call, Throwable t) {

            }
        });
    }

    private static String getValue() {
        return "asdsa";
    }

    private static void syso(String msg) {
        System.out.println(msg);
    }
}
