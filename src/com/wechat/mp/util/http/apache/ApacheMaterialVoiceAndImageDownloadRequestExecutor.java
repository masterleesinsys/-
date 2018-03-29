package com.wechat.mp.util.http.apache;

import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.common.util.http.apache.InputStreamResponseHandler;
import com.wechat.common.util.json.WxGsonBuilder;
import com.wechat.mp.util.http.MaterialVoiceAndImageDownloadRequestExecutor;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017/5/5.
 */
public class ApacheMaterialVoiceAndImageDownloadRequestExecutor extends MaterialVoiceAndImageDownloadRequestExecutor<CloseableHttpClient, HttpHost> {
  public ApacheMaterialVoiceAndImageDownloadRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public InputStream execute(String uri, String materialId) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }

    Map<String, String> params = new HashMap<>();
    params.put("media_id", materialId);
    httpPost.setEntity(new StringEntity(WxGsonBuilder.create().toJson(params)));
    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost);
         InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response)) {
      // 下载媒体文件出错
      byte[] responseContent = IOUtils.toByteArray(inputStream);
      String responseContentString = new String(responseContent, StandardCharsets.UTF_8);
      if (responseContentString.length() < 100) {
        try {
          WxError wxError = WxGsonBuilder.create().fromJson(responseContentString, WxError.class);
          if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
          }
        } catch (com.google.gson.JsonSyntaxException ex) {
          return new ByteArrayInputStream(responseContent);
        }
      }
      return new ByteArrayInputStream(responseContent);
    } finally {
      httpPost.releaseConnection();
    }
  }
}