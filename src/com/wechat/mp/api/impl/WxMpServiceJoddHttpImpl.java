package com.wechat.mp.api.impl;

import jodd.http.*;
import jodd.http.net.SocketHttpConnectionProvider;

import com.wechat.common.bean.WxAccessToken;
import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.HttpType;
import com.wechat.mp.api.WxMpConfigStorage;
import com.wechat.mp.api.WxMpService;

import java.util.concurrent.locks.Lock;

/**
 * jodd-http方式实现
 */
public class WxMpServiceJoddHttpImpl extends WxMpServiceAbstractImpl<HttpConnectionProvider, ProxyInfo> {
  private HttpConnectionProvider httpClient;
  private ProxyInfo httpProxy;

  @Override
  public HttpConnectionProvider getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public ProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.JODD_HTTP;
  }

  @Override
  public void initHttp() {

    WxMpConfigStorage configStorage = this.getWxMpConfigStorage();

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = new ProxyInfo(ProxyInfo.ProxyType.HTTP, configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }

    httpClient = JoddHttp.httpConnectionProvider;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    Lock lock = this.getWxMpConfigStorage().getAccessTokenLock();
    try {
      lock.lock();

      if (this.getWxMpConfigStorage().isAccessTokenExpired() || forceRefresh) {
        String url = String.format(WxMpService.GET_ACCESS_TOKEN_URL,
          this.getWxMpConfigStorage().getAppId(), this.getWxMpConfigStorage().getSecret());

        HttpRequest request = HttpRequest.get(url);

        if (this.getRequestHttpProxy() != null) {
          SocketHttpConnectionProvider provider = new SocketHttpConnectionProvider();
          provider.useProxy(getRequestHttpProxy());

          request.withConnectionProvider(provider);
        }
        HttpResponse response = request.send();
        String resultContent = response.bodyText();
        WxError error = WxError.fromJson(resultContent);
        if (error.getErrorCode() != 0) {
          throw new WxErrorException(error);
        }
        WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
        this.getWxMpConfigStorage().updateAccessToken(accessToken.getAccessToken(),
          accessToken.getExpiresIn());
      }
    } finally {
      lock.unlock();
    }
    return this.getWxMpConfigStorage().getAccessToken();
  }

}
