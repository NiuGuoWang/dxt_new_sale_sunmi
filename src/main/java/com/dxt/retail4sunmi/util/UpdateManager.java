package com.dxt.retail4sunmi.util;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.activity.LoginActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class UpdateManager {

    private Context mContext;
    //提示语
    private String updateMsg = "有最新的软件包哦，亲快下载吧~";

    //返回的安装包url   
    private String apkUrl;
    private String isMust;

    private Dialog noticeDialog;

    private Dialog downloadDialog;
    /* 下载包安装路径 */


    private static String savePath = null;

    private static String saveFileName = null;

    /* 进度条与通知ui刷新的handler和msg常量 */
    private ProgressBar mProgress;


    private static final int DOWN_UPDATE = 1;

    private static final int DOWN_OVER = 2;

    private int progress;

    private Thread downLoadThread;

    private boolean interceptFlag = false;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    mProgress.setProgress(progress);
                    break;
                case DOWN_OVER:
                    downloadDialog.dismiss();
                    installApk();
                    break;
                default:
                    break;
            }
        }
    };


    public UpdateManager(Context context, String url, String isMust) {
        this.mContext = context;
        this.apkUrl = url;
        this.isMust = isMust;
    }

    //外部接口让主Activity调用   
    public void checkUpdateInfo() {
        showNoticeDialog();
    }


    private void showNoticeDialog() {
        Builder builder = new Builder(mContext);
        builder.setCancelable(false);
        builder.setTitle("软件版本更新");
        builder.setMessage(updateMsg);
        builder.setPositiveButton("下载", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showDownloadDialog();
            }
        });
        builder.setNegativeButton("0".equals(isMust) ? "以后再说" : "退出程序", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if ("1".equals(isMust)) {
                    System.exit(0);
                } else {
                    dialog.dismiss();
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
        noticeDialog = builder.create();
        noticeDialog.show();
    }

    private void showDownloadDialog() {
        Builder builder = new Builder(mContext);
        builder.setTitle("软件版本更新");

        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.progress, null);
        mProgress = v.findViewById(R.id.progress);
        builder.setView(v);
        downloadDialog = builder.create();
        downloadDialog.setCancelable(false);
        downloadDialog.show();

        downloadApk();
    }

    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    savePath = Environment.getExternalStorageDirectory().getPath() + "/dxtNewSell/";
                    saveFileName = savePath + "dxtNewSell.apk";
                    URL url = new URL(apkUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String apkFile = saveFileName;
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);
                    int count = 0;
                    byte[] buf = new byte[1024];
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        //更新进度   
                        mHandler.sendEmptyMessage(DOWN_UPDATE);
                        if (numread <= 0) {
                            //下载完成通知安装   
                            mHandler.sendEmptyMessage(DOWN_OVER);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!interceptFlag);//点击取消就停止下载.
                    fos.close();
                    is.close();
                } else {
                    ToastUtils.showLongSafe("请检查您的网络!");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                if (downloadDialog != null) {
                    downloadDialog.dismiss();
                }
                ToastUtils.showLongSafe("网络异常");
            }

        }
    };

    /**
     * 下载apk
     */

    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    /**
     * 安装apk
     */
    private void installApk() {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        int osVersion = Build.VERSION.SDK_INT;
        if (osVersion > 23) {
            Uri uri = FileProvider.getUriForFile(
                    mContext,
                    mContext.getPackageName() + ".fileprovider",
                    apkfile);
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction("android.intent.action.VIEW");
            localIntent.setDataAndType(uri, "application/vnd.android.package-archive");
            mContext.startActivity(localIntent);
        } else {
            Intent localIntent = new Intent();
            localIntent.setAction("android.intent.action.VIEW");
            localIntent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(localIntent);
        }
    }
}  
