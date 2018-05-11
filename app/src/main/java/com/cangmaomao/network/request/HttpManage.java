package com.cangmaomao.network.request;


import com.cangmaomao.network.request.base.BaseData;
import com.cangmaomao.network.request.base.BaseRetrofit;
import com.cangmaomao.network.request.config.Config;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManage {

    private Retrofit retrofit;

    private static class HttpManageHolder {
        private static final HttpManage INSTANCE = new HttpManage();
    }

    public static HttpManage getInstance() {
        return HttpManageHolder.INSTANCE;
    }


    HttpManage() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.S_HTTP_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    private <T, K> T createClass(BaseRetrofit<T, K> baseRetrofit) {
        return baseRetrofit.setRetrofit(retrofit);
    }

    @SuppressWarnings("ALL")
    public void map(BaseRetrofit baseRetrofit, Observer observer) {
        post(observer, (Observable) baseRetrofit.getClassApi(createClass(baseRetrofit)));
    }

    @SuppressWarnings("ALL")
    public <K1, K2> void post(Observer<K1> observer, Observable<K2> observable) {
        observable.map(new Function<K2, K1>() {
            @Override
            public K1 apply(@NonNull K2 t2) throws Exception {
                return (K1) t2;
            }
        })
                .subscribeOn(Schedulers.io())//指定网络请求在io线程
                .observeOn(AndroidSchedulers.mainThread())//指定返回结果处理在主线程，这样我们就可以在onnext中更新ui了
                .subscribe(observer);
    }




}
