package com.wechat.mp.bean.material;

import lombok.Data;

import java.io.Serializable;

import com.wechat.mp.util.json.WxMpGsonBuilder;

@Data
public class WxMediaImgUploadResult implements Serializable {
  private static final long serialVersionUID = 1996392453428768829L;
  private String url;

  public static WxMediaImgUploadResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMediaImgUploadResult.class);
  }

}
