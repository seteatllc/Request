package com.cangmaomao.network.request.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseObserver<T> implements Observer<T> {

    private BaseResponseListener<T> listener;


    public BaseObserver(BaseResponseListener listener) {
        this.listener = listener;
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        listener.success(t);
    }

    @Override
    public void onError(Throwable e) {
        listener.fail(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
