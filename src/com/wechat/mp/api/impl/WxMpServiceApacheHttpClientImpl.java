package com.wechat.mp.api.impl;

import com.wechat.common.bean.WxAccessToken;
import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.HttpType;
import com.wechat.common.util.http.apache.ApacheHttpClientBuilder;
import com.wechat.common.util.http.apache.DefaultApacheHttpClientBuilder;
import com.wechat.mp.api.WxMpConfigStorage;
import com.wechat.mp.api.WxMpService;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

/**
 * apache-http方式实现
 */
public class WxMpServiceApacheHttpClientImpl extends WxMpServiceAbstractImpl<CloseableHttpClient, HttpHost> {
  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;

  @Override
  public CloseableHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public HttpHost getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.APACHE_HTTP;
  }

  @Override
  public void initHttp() {
    WxMpConfigStorage configStorage = this.getWxMpConfigStorage();
    ApacheHttpClientBuilder apacheHttpClientBuilder = configStorage.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
      .httpProxyPort(configStorage.getHttpProxyPort())
      .httpProxyUsername(configStorage.getHttpProxyUsername())
      .httpProxyPassword(configStorage.getHttpProxyPassword());

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    Lock lock = this.getWxMpConfigStorage().getAccessTokenLock();
    try {
      lock.lock();
      if (this.getWxMpConfigStorage().isAccessTokenExpired() || forceRefresh) {
        String url = String.format(WxMpService.GET_ACCESS_TOKEN_URL,
          this.getWxMpConfigStorage().getAppId(), this.getWxMpConfigStorage().getSecret());
        try {
          HttpGet httpGet = new HttpGet(url);
          if (this.getRequestHttpProxy() != null) {
            RequestConfig config = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
            httpGet.setConfig(config);
          }
          try (CloseableHttpResponse response = getRequestHttpClient().execute(httpGet)) {
            String resultContent = new BasicResponseHandler().handleResponse(response);
            WxError error = WxError.fromJson(resultContent);
            if (error.getErrorCode() != 0) {
              throw new WxErrorException(error);
            }
            WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
            this.getWxMpConfigStorage().updateAccessToken(accessToken.getAccessToken(),
              accessToken.getExpiresIn());
          } finally {
            httpGet.releaseConnection();
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    } finally {
      lock.unlock();
    }
    return this.getWxMpConfigStorage().getAccessToken();
  }
}
