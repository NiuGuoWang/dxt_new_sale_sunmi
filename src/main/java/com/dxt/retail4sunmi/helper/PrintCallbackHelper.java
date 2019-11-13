package com.dxt.retail4sunmi.helper;

import woyou.aidlservice.jiuiv5.ICallback;

/**
 * star on 2018/6/19 0019.
 */
public class PrintCallbackHelper {

    private static ICallback callback;

    public static ICallback getCallback() {
        if (callback == null) {
            callback = new ICallback.Stub() {

                @Override
                public void onRunResult(boolean success) {
                }

                @Override
                public void onReturnString(final String value) {
                }

                @Override
                public void onRaiseException(int code, final String msg) {
                }

                @Override
                public void onPrintResult(int code, String msg) {
                }
            };
        }
        return callback;
    }

}
