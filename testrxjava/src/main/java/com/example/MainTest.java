package com.example;


import com.example.entity.IpInfo;
import com.example.response.BaseResponse;
import com.example.service.TaoBaoApiService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
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

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(httpLoggingInterceptor);
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                //获得请求信息，此处如有需要可以添加headers信息
                Request request = chain.request();
                request = request.newBuilder().addHeader("Cookie", "aaaa").build();
                //打印请求信息
                syso("url:" + request.url());
                syso("method:" + request.method());
                syso("body:" + request.body());

                syso("request的Headers==========");
                Headers requestHeaders = request.headers();
                syso(requestHeaders.toString());

                //记录请求耗时
                long startNs = System.nanoTime();
                okhttp3.Response response;
                try {
                    //发送请求
                    response = chain.proceed(request);
                } catch (Exception e) {
                    throw e;
                }
                long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
                //打印请求耗时
                syso("耗时:"+tookMs+"ms");

                syso("响应代码:" + response.code() + "，响应消息:" + response.message());

                //获得返回的body，注意此处不要使用responseBody.string()获取返回数据，原因在于这个方法会消耗返回结果的数据(buffer)
                ResponseBody responseBody = response.body();

                //为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                //获得返回的数据
                Buffer buffer = source.buffer();
                //使用前clone()下，避免直接消耗
                syso("response:" + buffer.clone().readString(Charset.forName("UTF-8")));
                return response;
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ip.taobao.com").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient.build()).build();
        TaoBaoApiService taoBaoApiService = retrofit.create(TaoBaoApiService.class);

        Call<BaseResponse<IpInfo>> call = taoBaoApiService.getIpInfo("220.248.17.90");
        call.enqueue(new Callback<BaseResponse<IpInfo>>() {
            @Override
            public void onResponse(Call<BaseResponse<IpInfo>> call, Response<BaseResponse<IpInfo>> response) {
                BaseResponse<IpInfo> ipInfoRespon = response.body();
                IpInfo ipInfo = ipInfoRespon.getData();
                syso("结果");
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
