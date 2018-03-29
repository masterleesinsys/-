package com.wechat.mp.util.http;

import com.wechat.common.util.http.RequestExecutor;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.mp.bean.material.WxMediaImgUploadResult;
import com.wechat.mp.util.http.apache.ApacheMediaImgUploadRequestExecutor;
import com.wechat.mp.util.http.jodd.JoddMediaImgUploadRequestExecutor;
import com.wechat.mp.util.http.okhttp.OkhttpMediaImgUploadRequestExecutor;

import java.io.File;

/**
 * @author miller
 */
public abstract class MediaImgUploadRequestExecutor<H, P> implements RequestExecutor<WxMediaImgUploadResult, File> {
  protected RequestHttp<H, P> requestHttp;

  public MediaImgUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<WxMediaImgUploadResult, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMediaImgUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddMediaImgUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkhttpMediaImgUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
