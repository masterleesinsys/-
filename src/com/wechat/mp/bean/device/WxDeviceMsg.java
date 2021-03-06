package com.wechat.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import com.wechat.common.util.ToStringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 10/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxDeviceMsg extends AbstractDeviceBean {
  private static final long serialVersionUID = -5567110858455277963L;

  @SerializedName("device_type")
  private String deviceType;
  @SerializedName("device_id")
  private String deviceId;
  @SerializedName("open_id")
  private String openId;
  private String content;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}
