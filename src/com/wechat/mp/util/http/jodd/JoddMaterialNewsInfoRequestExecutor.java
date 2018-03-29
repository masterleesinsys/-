package com.wechat.mp.util.http.jodd;

import com.google.common.collect.ImmutableMap;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.common.util.json.WxGsonBuilder;
import com.wechat.mp.bean.material.WxMpMaterialNews;
import com.wechat.mp.util.http.MaterialNewsInfoRequestExecutor;
import com.wechat.mp.util.json.WxMpGsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created on 2017/5/5.
 */
public class JoddMaterialNewsInfoRequestExecutor extends MaterialNewsInfoRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  public JoddMaterialNewsInfoRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMpMaterialNews execute(String uri, String materialId) throws WxErrorException, IOException {
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }

    HttpRequest request = HttpRequest.post(uri)
      .withConnectionProvider(requestHttp.getRequestHttpClient())
      .body(WxGsonBuilder.create().toJson(ImmutableMap.of("media_id", materialId)));
    HttpResponse response = request.send();
    response.charset(StringPool.UTF_8);

    String responseContent = response.bodyText();
    this.logger.debug("响应原始数据：{}", responseContent);
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpGsonBuilder.create().fromJson(responseContent, WxMpMaterialNews.class);
    }
  }
}
