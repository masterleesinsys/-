package com.wechat.mp.util.http.okhttp;

import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.common.util.http.okhttp.OkHttpProxyInfo;
import com.wechat.common.util.json.WxGsonBuilder;
import com.wechat.mp.util.http.MaterialVoiceAndImageDownloadRequestExecutor;

import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created on 2017/5/5.
 */
public class OkhttpMaterialVoiceAndImageDownloadRequestExecutor extends MaterialVoiceAndImageDownloadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public OkhttpMaterialVoiceAndImageDownloadRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public InputStream execute(String uri, String materialId) throws WxErrorException, IOException {
    logger.debug("OkhttpMaterialVoiceAndImageDownloadRequestExecutor is running");
    OkHttpClient client = requestHttp.getRequestHttpClient();
    RequestBody requestBody = new FormBody.Builder().add("media_id", materialId).build();
    Request request = new Request.Builder().url(uri).get().post(requestBody).build();
    Response response = client.newCall(request).execute();
    String contentTypeHeader = response.header("Content-Type");
    if ("text/plain".equals(contentTypeHeader)) {
      String responseContent = response.body().string();
      throw new WxErrorException(WxError.fromJson(responseContent));
    }
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); BufferedSink sink = Okio.buffer(Okio.sink(outputStream))) {
      sink.writeAll(response.body().source());
      return new ByteArrayInputStream(outputStream.toByteArray());
    }
  }
}
