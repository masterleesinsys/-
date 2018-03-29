package com.wechat.mp.bean.result;

import lombok.Data;

import com.wechat.common.util.ToStringUtils;
import com.wechat.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

@Data
public class WxMpOAuth2AccessToken implements Serializable {
  private static final long serialVersionUID = -1345910558078620805L;

  private String accessToken;

  private int expiresIn = -1;

  private String refreshToken;

  private String openId;

  private String scope;

  private String unionId;

  public static WxMpOAuth2AccessToken fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpOAuth2AccessToken.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}
