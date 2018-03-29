package com.wechat.mp.util.http.okhttp;

import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.common.util.http.okhttp.OkHttpProxyInfo;
import com.wechat.mp.bean.material.WxMediaImgUploadResult;
import com.wechat.mp.util.http.MediaImgUploadRequestExecutor;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created on 2017/5/5.
 */
public class OkhttpMediaImgUploadRequestExecutor extends MediaImgUploadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public OkhttpMediaImgUploadRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMediaImgUploadResult execute(String uri, File file) throws WxErrorException, IOException {
    logger.debug("OkhttpMediaImgUploadRequestExecutor is running");
    //得到httpClient
    OkHttpClient client = requestHttp.getRequestHttpClient();

    RequestBody body = new MultipartBody.Builder()
      .setType(MediaType.parse("multipart/form-data"))
      .addFormDataPart("media",
        file.getName(),
        RequestBody.create(MediaType.parse("application/octet-stream"), file))
      .build();

    Request request = new Request.Builder().url(uri).post(body).build();
    Response response = client.newCall(request).execute();
    String responseContent = response.body().string();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return WxMediaImgUploadResult.fromJson(responseContent);
  }
}
