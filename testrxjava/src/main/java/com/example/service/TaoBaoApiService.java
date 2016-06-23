package com.example.service;

import com.example.entity.IpInfo;
import com.example.response.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/6/23.
 */

public interface TaoBaoApiService {
    @GET("service/getIpInfo.php")
    Call<BaseResponse<IpInfo>> getIpInfo(@Query("ip") String ip);
}
