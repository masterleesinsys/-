package com.wechat.mp.bean.material;

import lombok.Data;

import java.io.Serializable;

import com.wechat.mp.util.json.WxMpGsonBuilder;

@Data
public class WxMpMaterialVideoInfoResult implements Serializable {
  private static final long serialVersionUID = 1269131745333792202L;

  private String title;
  private String description;
  private String downUrl;

  public static WxMpMaterialVideoInfoResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMaterialVideoInfoResult.class);
  }

}
