package com.wechat.mp.util.http;

import com.wechat.common.util.http.RequestExecutor;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.mp.bean.material.WxMpMaterial;
import com.wechat.mp.bean.material.WxMpMaterialUploadResult;
import com.wechat.mp.util.http.apache.ApacheMaterialUploadRequestExecutor;
import com.wechat.mp.util.http.jodd.JoddMaterialUploadRequestExecutor;
import com.wechat.mp.util.http.okhttp.OkhttpMaterialUploadRequestExecutor;

public abstract class MaterialUploadRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMaterialUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddMaterialUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkhttpMaterialUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
