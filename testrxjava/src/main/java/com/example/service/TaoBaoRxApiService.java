package com.example.service;

import com.example.entity.IpInfo;
import com.example.response.BaseResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/23.
 */

public interface TaoBaoRxApiService {
    @GET("service/getIpInfo.php")
    Observable<BaseResponse<IpInfo>> getIpInfo(@Query("ip") String ip);
}
