package com.wechat.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import com.wechat.mp.util.json.WxMpGsonBuilder;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 10/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxDeviceQrCodeResult extends AbstractDeviceBean {
  private static final long serialVersionUID = -4312858303060918266L;

  @SerializedName("deviceid")
  private String deviceId;
  @SerializedName("qrticket")
  private String qrTicket;
  @SerializedName("devicelicence")
  private String deviceLicence;
  @SerializedName("base_resp")
  private BaseResp baseResp;

  public static WxDeviceQrCodeResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxDeviceQrCodeResult.class);
  }

}
