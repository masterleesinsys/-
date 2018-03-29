package com.wechat.mp.bean.device;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.wechat.common.util.json.WxGsonBuilder;

/**
 * @date 10/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxDeviceAuthorizeResult extends AbstractDeviceBean {
  private static final long serialVersionUID = 9105294570912249811L;

  private List<BaseResp> resp;

  public static WxDeviceAuthorizeResult fromJson(String response) {
    return WxGsonBuilder.create().fromJson(response, WxDeviceAuthorizeResult.class);
  }

}
