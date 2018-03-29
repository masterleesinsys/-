package com.wechat.mp.util.http;

import com.wechat.common.util.http.RequestExecutor;
import com.wechat.common.util.http.RequestHttp;
import com.wechat.mp.bean.material.WxMpMaterialVideoInfoResult;
import com.wechat.mp.util.http.apache.ApacheMaterialVideoInfoRequestExecutor;
import com.wechat.mp.util.http.jodd.JoddMaterialVideoInfoRequestExecutor;
import com.wechat.mp.util.http.okhttp.OkhttpMaterialVideoInfoRequestExecutor;


public abstract class MaterialVideoInfoRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialVideoInfoResult, String> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialVideoInfoRequestExecutor(RequestHttp requestHttp) {
          this.requestHttp = requestHttp;
        }

        public static RequestExecutor<WxMpMaterialVideoInfoResult, String> create(RequestHttp requestHttp) {
          switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
              return new ApacheMaterialVideoInfoRequestExecutor(requestHttp);
            case JODD_HTTP:
              return new JoddMaterialVideoInfoRequestExecutor(requestHttp);
            case OK_HTTP:
              return new OkhttpMaterialVideoInfoRequestExecutor(requestHttp);
            default:
              return null;
          }
        }

      }
