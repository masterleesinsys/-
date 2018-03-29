package com.wechat.mp.util.http.apache;

import com.google.common.collect.ImmutableMap;
import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.common.util.http.apache.Utf8ResponseHandler;
import com.wechat.common.util.json.WxGsonBuilder;
import com.wechat.mp.bean.material.WxMpMaterialNews;
import com.wechat.mp.util.http.MaterialNewsInfoRequestExecutor;
import com.wechat.mp.util.json.WxMpGsonBuilder;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * httpclient 实现的素材请求执行器.
 * @date 2017/5/5
 */
public class ApacheMaterialNewsInfoRequestExecutor
    extends MaterialNewsInfoRequestExecutor<CloseableHttpClient, HttpHost> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public ApacheMaterialNewsInfoRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMpMaterialNews execute(String uri, String materialId) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }

    httpPost.setEntity(new StringEntity(WxGsonBuilder.create().toJson(ImmutableMap.of("media_id", materialId))));
    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      this.logger.debug("响应原始数据：{}", responseContent);
      WxError error = WxError.fromJson(responseContent);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      } else {
        return WxMpGsonBuilder.create().fromJson(responseContent, WxMpMaterialNews.class);
      }
    } finally {
      httpPost.releaseConnection();
    }
  }
}
