package com.wechat.mp.util.http;

import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.RequestExecutor;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.mp.bean.result.WxMpQrCodeTicket;
import com.wechat.mp.util.http.apache.ApacheQrCodeRequestExecutor;
import com.wechat.mp.util.http.jodd.JoddQrCodeRequestExecutor;
import com.wechat.mp.util.http.okhttp.OkhttpQrCodeRequestExecutor;

import java.io.File;

/**
 * 获得QrCode图片 请求执行器
 */
public abstract class QrCodeRequestExecutor<H, P> implements RequestExecutor<File, WxMpQrCodeTicket> {
  protected RequestHttp<H, P> requestHttp;

  public QrCodeRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<File, WxMpQrCodeTicket> create(RequestHttp requestHttp) throws WxErrorException {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheQrCodeRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddQrCodeRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkhttpQrCodeRequestExecutor(requestHttp);
      default:
        throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("不支持的http框架").build());
    }
  }

}
