package com.wechat.mp.bean.result;

import lombok.Data;

import java.io.Serializable;

import com.wechat.mp.util.json.WxMpGsonBuilder;

/**
 * 换取二维码的Ticket
 */
@Data
public class WxMpQrCodeTicket implements Serializable {
  private static final long serialVersionUID = 5777119669111011584L;

  protected String ticket;
  /**
   * 如果为-1，说明是永久
   */
  protected int expireSeconds = -1;
  protected String url;

  public static WxMpQrCodeTicket fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpQrCodeTicket.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }
}
