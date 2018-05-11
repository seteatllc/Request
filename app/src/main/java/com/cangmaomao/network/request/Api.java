package com.cangmaomao.network.request;

import com.cangmaomao.network.request.base.BaseData;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("Login/app_bus_login")
    Observable<Login> login(@FieldMap Map<String, String> map);
}
