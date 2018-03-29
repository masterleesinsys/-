package com.wechat.mp.util.http.okhttp;

import com.google.common.collect.ImmutableMap;
import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.common.util.http.okhttp.OkHttpProxyInfo;
import com.wechat.common.util.json.WxGsonBuilder;
import com.wechat.mp.bean.material.WxMpMaterialNews;
import com.wechat.mp.util.http.MaterialNewsInfoRequestExecutor;
import com.wechat.mp.util.json.WxMpGsonBuilder;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created on 2017/5/5.
 */
public class OkhttpMaterialNewsInfoRequestExecutor extends MaterialNewsInfoRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  public OkhttpMaterialNewsInfoRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMpMaterialNews execute(String uri, String materialId) throws WxErrorException, IOException {

    //得到httpClient
    OkHttpClient client = requestHttp.getRequestHttpClient();

    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
      WxGsonBuilder.create().toJson(ImmutableMap.of("media_id", materialId)));
    Request request = new Request.Builder().url(uri).post(requestBody).build();

    Response response = client.newCall(request).execute();
    String responseContent = response.body().string();
    this.logger.debug("响应原始数据：{}", responseContent);

    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpGsonBuilder.create().fromJson(responseContent, WxMpMaterialNews.class);
    }
  }
}
