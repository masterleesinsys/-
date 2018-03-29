package com.wechat.mp.bean.menu;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import com.wechat.common.util.ToStringUtils;
import com.wechat.common.util.json.WxGsonBuilder;

import java.io.Serializable;

@Data
public class WxMpGetSelfMenuInfoResult implements Serializable {
  private static final long serialVersionUID = -5612495636936835166L;

  @SerializedName("selfmenu_info")
  private WxMpSelfMenuInfo selfMenuInfo;

  @SerializedName("is_menu_open")
  private Integer isMenuOpen;

  public static WxMpGetSelfMenuInfoResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGetSelfMenuInfoResult.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
