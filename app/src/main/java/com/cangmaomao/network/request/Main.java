package com.cangmaomao.network.request;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cangmaomao.network.request.base.BaseObserver;
import com.cangmaomao.network.request.base.BaseResponseListener;
import com.cangmaomao.network.request.base.BaseRetrofit;
import com.cangmaomao.network.request.config.Config;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Map<String, String> params = new HashMap<>();
        params.put("devid", "40149f7d-4e06-3c08-90d7-950e8f94a4b2");
        params.put("buscode", "b1001");
        params.put("loginid", "123");
        params.put("password", "123");
        params.put("ct", "1");


        HttpManage.getInstance().map(new BaseRetrofit<Api, Observable>() {
            @Override
            public Api setRetrofit(Retrofit retrofit) {
                return retrofit.create(Api.class);
            }

            @Override
            public Observable getClassApi(Api api) {
                return api.login(params);
            }

        }, new BaseObserver(new BaseResponseListener<Login>() {
            @Override
            public void success(Login login) {

            }

            @Override
            public void fail(String err) {

            }
        }));
    }
}
