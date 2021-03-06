package com.wechat.mp.util.http.jodd;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.common.util.json.WxGsonBuilder;
import com.wechat.mp.bean.material.WxMpMaterial;
import com.wechat.mp.bean.material.WxMpMaterialUploadResult;
import com.wechat.mp.util.http.MaterialUploadRequestExecutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Created on 2017/5/5.
 */
public class JoddMaterialUploadRequestExecutor extends MaterialUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  public JoddMaterialUploadRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMpMaterialUploadResult execute(String uri, WxMpMaterial material) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());

    if (material == null) {
      throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("非法请求，material参数为空").build());
    }

    File file = material.getFile();
    if (file == null || !file.exists()) {
      throw new FileNotFoundException();
    }
    request.form("media", file);
    Map<String, String> form = material.getForm();
    if (material.getForm() != null) {
      request.form("description", WxGsonBuilder.create().toJson(form));
    }

    HttpResponse response = request.send();
    response.charset(StringPool.UTF_8);
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpMaterialUploadResult.fromJson(responseContent);
    }
  }
}
