package com.wechat.mp.util.http.okhttp;

import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.common.util.http.okhttp.OkHttpProxyInfo;
import com.wechat.common.util.json.WxGsonBuilder;
import com.wechat.mp.bean.material.WxMpMaterial;
import com.wechat.mp.bean.material.WxMpMaterialUploadResult;
import com.wechat.mp.util.http.MaterialUploadRequestExecutor;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Created on 2017/5/5.
 */
public class OkhttpMaterialUploadRequestExecutor extends MaterialUploadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public OkhttpMaterialUploadRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMpMaterialUploadResult execute(String uri, WxMpMaterial material) throws WxErrorException, IOException {
    logger.debug("OkhttpMaterialUploadRequestExecutor is running");
    if (material == null) {
      throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("非法请求，material参数为空").build());
    }
    File file = material.getFile();
    if (file == null || !file.exists()) {
      throw new FileNotFoundException();
    }

    //得到httpClient

    OkHttpClient client = requestHttp.getRequestHttpClient();

    MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
      .setType(MediaType.parse("multipart/form-data"))
      .addFormDataPart("media",
        file.getName(),
        RequestBody.create(MediaType.parse("application/octet-stream"), file));
    Map<String, String> form = material.getForm();
    if (form != null) {
      bodyBuilder.addFormDataPart("description", WxGsonBuilder.create().toJson(form));
    }

    Request request = new Request.Builder().url(uri).post(bodyBuilder.build()).build();
    Response response = client.newCall(request).execute();
    String responseContent = response.body().string();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpMaterialUploadResult.fromJson(responseContent);
    }
  }

}
