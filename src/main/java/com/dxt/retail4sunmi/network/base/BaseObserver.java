package com.dxt.retail4sunmi.network.base;

import android.support.annotation.CallSuper;

import com.dxt.retail4sunmi.network.exception.HttpResponseException;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/29.
 */

public abstract class BaseObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {


        HttpResponseException responseException;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            responseException = new HttpResponseException("网络请求出错", httpException.code());
        } else if (e instanceof HttpResponseException) {
            responseException = (HttpResponseException) e;
        } else {//其他或者没网会走这里
            responseException = new HttpResponseException("网络异常,请稍后重试", -1024);
        }
        LogUtils.e(e);
        onFailed(responseException);
    }

    @Override
    public void onComplete() {
    }

    protected abstract void onSuccess(T t);

    @CallSuper
    private void onFailed(HttpResponseException responseException) {
        ToastUtils.showLong(responseException.getMessage() + "(" + responseException.getStatus() + ")");
    }
}
