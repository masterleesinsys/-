package com.wechat.mp.util.http.okhttp;

import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.fs.FileUtils;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.common.util.http.okhttp.OkHttpProxyInfo;
import com.wechat.mp.bean.result.WxMpQrCodeTicket;
import com.wechat.mp.util.http.QrCodeRequestExecutor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 *
 * @date 2017/5/5
 */
public class OkhttpQrCodeRequestExecutor extends QrCodeRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public OkhttpQrCodeRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public File execute(String uri, WxMpQrCodeTicket ticket) throws WxErrorException, IOException {
    logger.debug("OkhttpQrCodeRequestExecutor is running");

    if (ticket != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?")
        ? "ticket=" + URLEncoder.encode(ticket.getTicket(), "UTF-8")
        : "&ticket=" + URLEncoder.encode(ticket.getTicket(), "UTF-8");
    }

    OkHttpClient client = requestHttp.getRequestHttpClient();
    Request request = new Request.Builder().url(uri).get().build();
    Response response = client.newCall(request).execute();
    String contentTypeHeader = response.header("Content-Type");
    if ("text/plain".equals(contentTypeHeader)) {
      String responseContent = response.body().string();
      throw new WxErrorException(WxError.fromJson(responseContent));
    }

    try (InputStream inputStream = response.body().byteStream()) {
      return FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), "jpg");
    }

  }
}
