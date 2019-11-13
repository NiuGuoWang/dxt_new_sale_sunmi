package com.dxt.retail4sunmi.network.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by allen on 2016/12/21.
 * <p>
 * 定义请求结果处理接口
 */

public interface ISubscriber<T> {

    void doOnSubscribe(Disposable d);

    void doOnError(String errorMsg);

    void doOnNext(T t);

    void doOnCompleted();
}
