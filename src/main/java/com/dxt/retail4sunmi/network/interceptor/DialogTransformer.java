package com.dxt.retail4sunmi.network.interceptor;

import android.app.Activity;
import android.content.DialogInterface;

import com.dxt.retail4sunmi.view.CustomProgressDialog;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Description:
 * Company:
 * Email:bjxm2013@163.com
 * Created by Devin Sun on 2017/4/3.
 */
public class DialogTransformer {

    private static final String DEFAULT_MSG = "数据加载中...";

    private Activity activity;
    private String msg;
    private boolean cancelable;
    private CustomProgressDialog progressDialog;

    public DialogTransformer(Activity activity) {
        this(activity, DEFAULT_MSG);
    }

    private DialogTransformer(Activity activity, String msg) {
        this(activity, msg, true);
    }

    private DialogTransformer(Activity activity, String msg, boolean cancelable) {
        this.activity = activity;

        this.msg = msg;
        this.cancelable = cancelable;
    }

    public <T> ObservableTransformer<T, T> transformer() {
        return new ObservableTransformer<T, T>() {

            @Override
            public ObservableSource<T> apply(final Observable<T> upstream) {

                return  upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull final Disposable disposable) {
                        progressDialog= CustomProgressDialog.showLoading(activity, msg, cancelable);
                        if (cancelable) {
                            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    disposable.dispose();
                                }
                            });
                        }
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()) {
                            progressDialog.cancel();
                        }
                    }
                });
            }
        };
    }
}
