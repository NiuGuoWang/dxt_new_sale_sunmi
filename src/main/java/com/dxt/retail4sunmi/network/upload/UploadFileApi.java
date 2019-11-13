package com.dxt.retail4sunmi.network.upload;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by allen on 2017/6/15.
 * <p>
 * 文件上传
 */

interface UploadFileApi {

    @Multipart
    @POST
    Observable<ResponseBody> uploadImg(@Url String uploadUrl,
                                       @Part MultipartBody.Part file);
}
