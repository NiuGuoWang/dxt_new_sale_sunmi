package com.dxt.retail4sunmi.network.download;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.network.exception.ApiException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;

/**
 * Created by allen on 2017/6/13.
 *
 */

abstract class BaseDownloadObserver implements Observer<ResponseBody> {

    private Toast mToast;

    /**
     * 失败回调
     *
     * @param errorMsg
     */
    protected abstract void doOnError(String errorMsg);


    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof SocketTimeoutException) {
            setError(ApiException.ERROR_MSG_SOCKET_TIMEOUT_EXCEPTION);
        } else if (e instanceof ConnectException) {
            setError(ApiException.ERROR_MSG_CONNECT_EXCEPTION);
        } else if (e instanceof UnknownHostException) {
            setError(ApiException.ERROR_MSG_UNKNOWN_HOST_EXCEPTION);
        } else {
            doOnError(e.getMessage());
        }
    }

    private void setError(String errorMsg) {
        showToast(errorMsg);
        doOnError(errorMsg);
    }

    /**
     * Toast提示
     *
     * @param msg 提示内容
     */
    @SuppressLint("ShowToast")
    private void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(DxtSaleApplication.getInstance(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
