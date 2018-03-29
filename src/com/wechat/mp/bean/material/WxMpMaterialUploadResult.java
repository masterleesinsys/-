package com.wechat.mp.bean.material;

import lombok.Data;

import com.wechat.common.util.ToStringUtils;
import com.wechat.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

@Data
public class WxMpMaterialUploadResult implements Serializable {
  private static final long serialVersionUID = -128818731449449537L;
  private String mediaId;
  private String url;
  private Integer errCode;
  private String errMsg;

  public static WxMpMaterialUploadResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMaterialUploadResult.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}

