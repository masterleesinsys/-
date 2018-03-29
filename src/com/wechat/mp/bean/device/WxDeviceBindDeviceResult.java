package com.wechat.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import com.wechat.mp.util.json.WxMpGsonBuilder;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @date 16/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxDeviceBindDeviceResult extends AbstractDeviceBean {
  private static final long serialVersionUID = 725870295905935355L;

  @SerializedName("resp_msg")
  private RespMsg respMsg;
  @SerializedName("openid")
  private String openId;
  @SerializedName("device_list")
  private List<Device> devices;

  public static WxDeviceBindDeviceResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxDeviceBindDeviceResult.class);
  }

  @Data
  private class Device {
    @SerializedName("device_type")
    private String deviceType;
    @SerializedName("device_id")
    private String deviceId;

  }

}
